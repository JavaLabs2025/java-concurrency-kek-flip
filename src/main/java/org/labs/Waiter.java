package org.labs;

public class Waiter {
    private Integer bowlsCount;
    private Integer portionSize;

    Waiter() {
        this(Constants.PORTIONS_COUNT);
    }

    Waiter(Integer bowlsCount) {
        this(bowlsCount, Constants.PORTION_SIZE);
    }

    Waiter(Integer bowlsCount, Integer portionSize) {
        this.bowlsCount = bowlsCount;
        this.portionSize = portionSize;
    }

    synchronized Boolean hasMoreFood() {
        return bowlsCount > 0;
    }

    synchronized public Bowl pickBowl() throws Exception {
        if (bowlsCount == 0) {
            throw new Exception("No more portions left");
        }

        bowlsCount--;
        return new Bowl(portionSize);
    }
}
