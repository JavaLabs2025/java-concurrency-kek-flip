package org.labs;

import java.util.ArrayList;

import org.labs.logger.ConsoleLogger;
import org.labs.logger.Logger;
import org.labs.programmer.Programmer;
import org.labs.spoon.Spoon;
import org.labs.waiter.WaiterService;

public class App {
    public static void main(String[] args) {
        final Logger logger = new ConsoleLogger();

        final var waiterService = new WaiterService();

        final var spoons = new ArrayList<Spoon>();
        for (Integer i = 0; i < Constants.PROGRAMMERS_COUNT; i++) {
            spoons.add(new Spoon(i));
        }

        final var programmers = new ArrayList<Programmer>();
        for (Integer i = 0; i < Constants.PROGRAMMERS_COUNT; i++) {
            Integer leftSpoonIdx = i;
            Integer rightSpoonIdx = (i + 1) % spoons.size();

            programmers.add(new Programmer(i, waiterService, spoons.get(leftSpoonIdx), spoons.get(rightSpoonIdx)));
        }

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
            logger.log("Программист", programmer.getProgrammerId().toString(), "съел",
                    programmer.getEatenPortions().toString(),
                    "порций");
        }

        var servedPortions = waiterService.getServedPortions();
        for (final var portion : servedPortions.entrySet()) {
            logger.log("Официант", portion.getKey().toString(), "разнес", portion.getValue().toString(), "порций");
        }
    }
}