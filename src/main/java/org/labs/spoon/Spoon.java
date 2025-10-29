package org.labs.spoon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.labs.logger.ConsoleLogger;
import org.labs.logger.Logger;

public class Spoon {
    private final Integer id;
    private final Logger logger;
    private Lock lock = new ReentrantLock();

    public Spoon(Integer id) {
        this.id = id;
        this.logger = new ConsoleLogger("Ложка " + id);
    }

    public void take(String actor) {
        logger.log(actor, "пытается взять ложку");
        lock.lock();
        logger.log(actor, "взял ложку");
    }

    public void put(String actor) {
        lock.unlock();
        logger.log(actor, "положил ложку");
    }

    public Integer getId() {
        return id;
    }
}
