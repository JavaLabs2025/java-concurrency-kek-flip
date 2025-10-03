package org.labs;

import org.labs.loggers.ConsoleLogger;
import org.labs.loggers.Logger;
import org.labs.waiters.Bowl;
import org.labs.waiters.Waiter;

public class Programmer extends Thread {
    private final Integer id;
    private final Logger logger;

    private final Integer biteSize;

    private Integer portionsEaten = 0;
    private Bowl bowl = new Bowl();

    private final Waiter waiter;
    private final Spoon firstSpoon;
    private final Spoon secondSpoon;

    Programmer(Integer id, Waiter waiter, Spoon leftSpoon, Spoon rightSpoon) {
        this(id, waiter, leftSpoon, rightSpoon, Constants.BITE_SIZE, new ConsoleLogger());
    }

    Programmer(Integer id, Waiter waiter, Spoon leftSpoon, Spoon rightSpoon, Integer biteSize, Logger logger) {
        this.id = id;
        this.waiter = waiter;

        if (leftSpoon.getId() < rightSpoon.getId()) {
            this.firstSpoon = leftSpoon;
            this.secondSpoon = rightSpoon;
        } else {
            this.firstSpoon = rightSpoon;
            this.secondSpoon = leftSpoon;
        }

        this.biteSize = biteSize;
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.log("Программист", id.toString(), "начал есть");
        while (true) {
            if (!waiter.hasMoreFood()) {
                break;
            }

            logger.log("Программист", id.toString(), "просит новую порцию");
            try {
                bowl = waiter.pickBowl();
            } catch (Exception e) {
                break;
            }

            logger.log("Программист", id.toString(), "ждет ложку", firstSpoon.getId().toString());
            firstSpoon.lock.lock();
            logger.log("Программист", id.toString(), "взял ложку", firstSpoon.getId().toString());

            logger.log("Программист", id.toString(), "ждет ложку", secondSpoon.getId().toString());
            secondSpoon.lock.lock();
            logger.log("Программист", id.toString(), "взял ложку", secondSpoon.getId().toString());

            while (!bowl.isEmpty()) {
                bowl.eat(biteSize);
            }
            portionsEaten++;
            logger.log("Программист", id.toString(), "доел порцию");

            secondSpoon.lock.unlock();
            logger.log("Программист", id.toString(), "положил ложку", secondSpoon.getId().toString());

            firstSpoon.lock.unlock();
            logger.log("Программист", id.toString(), "положил ложку", firstSpoon.getId().toString());
        }
        logger.log("Программист", id.toString(), "наелся");
    }

    public Integer getProgrammerId() {
        return id;
    }
}
