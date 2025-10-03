package org.labs.resources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.labs.loggers.ConsoleLogger;
import org.labs.loggers.Logger;

public class Spoon {
    private final Integer id;
    private final Logger logger;
    private Lock lock = new ReentrantLock();

    public Spoon(Integer id) {
        this.id = id;
        this.logger = new ConsoleLogger("Ложка " + id.toString());
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
