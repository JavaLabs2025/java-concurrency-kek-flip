package org.labs.waiter;

import java.util.Random;

import org.labs.Constants;
import org.labs.logger.ConsoleLogger;
import org.labs.logger.Logger;

class Waiter {
    private final Integer id;

    private final Logger logger;
    private final Random random = new Random();

    private Integer serverPortions = 0;

    public Waiter(Integer id) {
        this.id = id;
        this.logger = new ConsoleLogger("Официант " + id);
    }

    public synchronized void serverPortion(String client) throws Exception {
        logger.log("понес порцию", client);
        Thread.sleep(random.nextInt(Constants.MAX_SERVER_TIME_MS));
        serverPortions++;
    }

    public Integer getId() {
        return id;
    }

    public Integer getServedPortions() {
        return serverPortions;
    }
}
