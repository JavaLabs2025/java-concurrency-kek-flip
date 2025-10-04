package org.labs;

import java.util.ArrayList;

import org.labs.programmer.Programmer;
import org.labs.spoon.Spoon;
import org.labs.waiter.WaiterService;

public class App {
    public static void main(String[] args) {
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
            System.out.println(new StringBuilder().append("Программист ").append(programmer.getProgrammerId())
                    .append(" съел ").append(programmer.getEatenPortions()).append(" порций").toString());
        }

        var servedPortions = waiterService.getServedPortions();
        for (final var portion : servedPortions.entrySet()) {
            System.out.println(new StringBuilder().append("Официант ").append(portion.getKey())
                    .append(" разнес ").append(portion.getValue()).append(" порций").toString());
        }
    }
}