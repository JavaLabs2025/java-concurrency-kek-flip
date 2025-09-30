package org.labs;

public class Programmer extends Thread {
    private final Integer id;

    private final Integer biteSize;

    private Integer eaten = 0;
    private Bowl bowl = new Bowl();

    private final Waiter waiter;
    private final Spoon leftSpoon;
    private final Spoon rightSpoon;

    Programmer(Integer id, Waiter waiter, Spoon leftSpoon, Spoon rightSpoon) {
        this(id, waiter, leftSpoon, rightSpoon, Constants.BITE_SIZE);
    }

    Programmer(Integer id, Waiter waiter, Spoon leftSpoon, Spoon rightSpoon, Integer biteSize) {
        this.id = id;
        this.waiter = waiter;
        this.leftSpoon = leftSpoon;
        this.rightSpoon = rightSpoon;
        this.biteSize = biteSize;

    }

    @Override
    public void run() {
        while (true) {
            if (!waiter.hasMoreFood()) {
                break;
            }

            if (bowl.isEmpty()) {
                try {
                    bowl = waiter.pickBowl();
                } catch (Exception e) {
                    break;
                }
            }

            if (leftSpoon.lock.tryLock() && rightSpoon.lock.tryLock()) {
                Integer bitten = bowl.eat(biteSize);
                eaten += bitten;

                leftSpoon.lock.unlock();
                rightSpoon.lock.unlock();
            }
        }
    }
}
