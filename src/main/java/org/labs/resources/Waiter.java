package org.labs.resources;

import org.labs.Constants;
import org.labs.loggers.ConsoleLogger;
import org.labs.loggers.Logger;

public class Waiter {
    private final Logger logger;

    private Integer bowlsCount;

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

    synchronized public void pickBowl() throws Exception {
        if (bowlsCount == 0) {
            throw new Exception("No more portions left");
        }

        bowlsCount--;
        logger.log("отдал миску.", "Осталось", bowlsCount.toString());
    }
}
