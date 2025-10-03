package org.labs.resources;

import java.util.Random;

import org.labs.Constants;
import org.labs.loggers.ConsoleLogger;
import org.labs.loggers.Logger;

public class Waiter {
    private final Logger logger;

    private Integer bowlsCount;
    private Random random = new Random();

    public Waiter() {
        this(Constants.PORTIONS_COUNT);
    }

    public Waiter(Integer bowlsCount) {
        this.bowlsCount = bowlsCount;
        this.logger = new ConsoleLogger("Официант");
    }

    synchronized public Boolean hasMoreFood() {
        return bowlsCount > 0;
    }

    synchronized public Bowl pickBowl() throws Exception {
        if (bowlsCount == 0) {
            throw new Exception("No more portions left");
        }

        bowlsCount--;
        logger.log("отдал миску.", "Осталось", bowlsCount.toString());

        Integer portionSize = random.nextInt(Constants.MAX_PORTION_SIZE - Constants.MIN_PORTION_SIZE + 1)
                + Constants.MIN_PORTION_SIZE;

        return new Bowl(portionSize);
    }
}
