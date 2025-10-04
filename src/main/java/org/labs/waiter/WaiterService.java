package org.labs.waiter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.labs.Constants;
import org.labs.logger.ConsoleLogger;
import org.labs.logger.Logger;

public class WaiterService {
    private final Logger logger;

    private final AtomicInteger portionsCount;

    private final List<Waiter> waiters;
    private final AtomicInteger nextWaiter;

    public WaiterService() {
        this(Constants.WAITERS_COUNT, Constants.PORTIONS_COUNT);
    }

    public WaiterService(Integer waitersCount, Integer portionsCount) {
        Waiter[] mutableWaiters = new Waiter[waitersCount];
        for (Integer i = 0; i < waitersCount; i++) {
            mutableWaiters[i] = new Waiter(i);
        }
        waiters = List.of(mutableWaiters);
        nextWaiter = new AtomicInteger(0);
        this.portionsCount = new AtomicInteger(portionsCount);
        logger = new ConsoleLogger("Сервис официантов");
    }

    public Boolean getPortion(String client) throws Exception {
        logger.log(client, "просит порцию");

        var idx = nextWaiter.getAndUpdate((val) -> (val + 1) % waiters.size());
        var waiter = waiters.get(idx);

        Integer remainingPortions = portionsCount.decrementAndGet();
        if (remainingPortions < 0) {
            logger.log(client, "узнал, что еда закончилась");
            return false;
        }

        waiter.serverPortion(client);
        logger.log(client, "получил порцию");
        logger.log("порций осталось", remainingPortions.toString());
        return true;
    }

    public Map<Integer, Integer> getServedPortions() {
        return waiters.stream().collect(Collectors.toMap(Waiter::getId, Waiter::getServedPortions));
    }
}
