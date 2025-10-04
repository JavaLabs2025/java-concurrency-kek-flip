package org.labs.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.labs.logger.ConsoleLogger;
import org.labs.logger.Logger;
import org.labs.programmer.Programmer;
import org.labs.spoon.Spoon;
import org.labs.waiter.WaiterService;

public class Restaurant {
    private final Logger logger;

    private final WaiterService waiters;
    private final List<Spoon> spoons = new ArrayList<>();
    private final List<Programmer> programmers = new ArrayList<>();

    public Restaurant(Integer programmresCount, Integer waitersCount, Integer portionsCount) {
        logger = new ConsoleLogger("Ресторан");

        waiters = new WaiterService(waitersCount, portionsCount);

        for (Integer i = 0; i < programmresCount; i++) {
            spoons.add(new Spoon(i));
        }

        for (Integer i = 0; i < programmresCount; i++) {
            Integer leftSpoonIdx = i;
            Integer rightSpoonIdx = (i + 1) % spoons.size();

            programmers.add(new Programmer(i, waiters, spoons.get(leftSpoonIdx), spoons.get(rightSpoonIdx)));
        }
    }

    public void startDinner() {
        for (var p : programmers) {
            p.start();
        }

        logger.log("начал ужин");

        for (var p : programmers) {
            try {
                p.join();
            } catch (InterruptedException e) {
            }
        }

        logger.log("закончил ужин");
    }

    public DinnerInfo getDinnerInfo() {
        Map<Integer, Integer> programmersEatenPortions = programmers.stream()
                .collect(Collectors.toMap(Programmer::getProgrammerId, Programmer::getEatenPortions));
        Map<Integer, Integer> waitersServedPortions = waiters.getServedPortions();
        return new DinnerInfo(programmersEatenPortions, waitersServedPortions);
    }
}
