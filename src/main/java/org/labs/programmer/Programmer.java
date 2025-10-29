package org.labs.programmer;

import java.util.Random;

import org.labs.Constants;
import org.labs.logger.ConsoleLogger;
import org.labs.logger.Logger;
import org.labs.spoon.Spoon;
import org.labs.waiter.WaiterService;

public class Programmer extends Thread {
    private final Integer id;
    private final Logger logger;
    private final Random random = new Random();

    private Integer eatenPortions = 0;

    private final WaiterService waiters;
    private final Spoon firstSpoon;
    private final Spoon secondSpoon;

    public Programmer(Integer id, WaiterService waiters, Spoon leftSpoon, Spoon rightSpoon) {
        super("Программист " + id);

        this.id = id;
        this.waiters = waiters;

        if (leftSpoon.getId() < rightSpoon.getId()) {
            this.firstSpoon = leftSpoon;
            this.secondSpoon = rightSpoon;
        } else {
            this.firstSpoon = rightSpoon;
            this.secondSpoon = leftSpoon;
        }

        this.logger = new ConsoleLogger(getName());
    }

    @Override
    public void run() {
        try {
            logger.log("начал есть");
            while (true) {

                logger.log("просит новую порцию");
                try {
                    if (!waiters.getPortion(getName())) {
                        break;
                    }
                } catch (Exception e) {
                    break;
                }

                firstSpoon.take(getName());
                secondSpoon.take(getName());

                Thread.sleep(random.nextInt(Constants.MAX_EATING_TIME_MS));
                eatenPortions++;
                logger.log("доел порцию");

                secondSpoon.put(getName());
                firstSpoon.put(getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getProgrammerId() {
        return id;
    }

    public Integer getEatenPortions() {
        return eatenPortions;
    }
}
