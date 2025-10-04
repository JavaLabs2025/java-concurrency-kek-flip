package org.labs.programmer;

import java.util.Random;

import org.labs.Constants;
import org.labs.loggers.ConsoleLogger;
import org.labs.loggers.Logger;
import org.labs.resources.Spoon;
import org.labs.resources.Waiter;

public class Programmer extends Thread {
    private final Integer id;
    private final Logger logger;
    private final Random random = new Random();

    private Integer portionsEaten = 0;

    private final Waiter waiter;
    private final Spoon firstSpoon;
    private final Spoon secondSpoon;

    public Programmer(Integer id, Waiter waiter, Spoon leftSpoon, Spoon rightSpoon) {
        super("Программист " + id.toString());

        this.id = id;
        this.waiter = waiter;

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
                if (!waiter.hasMoreFood()) {
                    break;
                }

                logger.log("просит новую порцию");
                try {
                    waiter.pickBowl();
                } catch (Exception e) {
                    break;
                }

                firstSpoon.take(getName());
                secondSpoon.take(getName());

                Thread.sleep(random.nextInt(Constants.MAX_EATING_TIME));
                logger.log("доел порцию");

                secondSpoon.put(getName());
                firstSpoon.put(getName());
            }

            logger.log("съел", portionsEaten.toString(), "порций");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getProgrammerId() {
        return id;
    }
}
