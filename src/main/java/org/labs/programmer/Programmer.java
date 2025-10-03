package org.labs.programmer;

import org.labs.Constants;
import org.labs.loggers.ConsoleLogger;
import org.labs.loggers.Logger;
import org.labs.resources.Bowl;
import org.labs.resources.Spoon;
import org.labs.resources.Waiter;

public class Programmer extends Thread {
    private final Integer id;
    private final Logger logger;

    private final Integer biteSize;

    private Integer portionsEaten = 0;
    private Bowl bowl = new Bowl();

    private final Waiter waiter;
    private final Spoon firstSpoon;
    private final Spoon secondSpoon;

    public Programmer(Integer id, Waiter waiter, Spoon leftSpoon, Spoon rightSpoon) {
        this(id, waiter, leftSpoon, rightSpoon, Constants.BITE_SIZE);
    }

    public Programmer(Integer id, Waiter waiter, Spoon leftSpoon, Spoon rightSpoon, Integer biteSize) {
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

        this.biteSize = biteSize;
        this.logger = new ConsoleLogger(getName());
    }

    @Override
    public void run() {
        logger.log("начал есть");
        while (true) {
            if (!waiter.hasMoreFood()) {
                break;
            }

            logger.log("просит новую порцию");
            try {
                bowl = waiter.pickBowl();
            } catch (Exception e) {
                break;
            }

            firstSpoon.take(getName());
            secondSpoon.take(getName());

            while (!bowl.isEmpty()) {
                bowl.eat(biteSize);
            }
            portionsEaten++;
            logger.log("доел порцию");

            secondSpoon.put(getName());
            firstSpoon.put(getName());
        }

        logger.log("съел", portionsEaten.toString());
    }

    public Integer getProgrammerId() {
        return id;
    }
}
