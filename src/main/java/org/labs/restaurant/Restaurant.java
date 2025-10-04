package org.labs.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.labs.logger.ConsoleLogger;
import org.labs.logger.Logger;
import org.labs.programmer.Programmer;
import org.labs.spoon.Spoon;
import org.labs.waiter.WaiterService;

public class Restaurant {
    private final Logger logger;

    private final WaiterService waiters;
    private final List<Spoon> spoons = new ArrayList<>();
    private final List<Programmer> programmers = new ArrayList<>();

    public Restaurant(Integer programmresCount, Integer waitersCount, Integer portionsCount) {
        logger = new ConsoleLogger("Ресторан");

        waiters = new WaiterService(waitersCount, portionsCount);

        for (Integer i = 0; i < programmresCount; i++) {
            spoons.add(new Spoon(i));
        }

        for (Integer i = 0; i < programmresCount; i++) {
            Integer leftSpoonIdx = i;
            Integer rightSpoonIdx = (i + 1) % spoons.size();

            programmers.add(new Programmer(i, waiters, spoons.get(leftSpoonIdx), spoons.get(rightSpoonIdx)));
        }
    }

    public void startDinner() {
        for (var p : programmers) {
            p.start();
        }

        for (var p : programmers) {
            try {
                p.join();
            } catch (InterruptedException e) {
            }
        }

        for (final var programmer : programmers) {
            logger.log(new StringBuilder().append("Программист ").append(programmer.getProgrammerId())
                    .append(" съел ").append(programmer.getEatenPortions()).append(" порций").toString());
        }

        var servedPortions = waiters.getServedPortions();
        for (final var portion : servedPortions.entrySet()) {
            logger.log(new StringBuilder().append("Официант ").append(portion.getKey())
                    .append(" разнес ").append(portion.getValue()).append(" порций").toString());
        }
    }
}
