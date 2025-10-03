package org.labs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Spoon {
    private final Integer id;

    public Lock lock = new ReentrantLock();

    Spoon(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
