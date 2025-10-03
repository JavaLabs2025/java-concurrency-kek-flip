package org.labs;

import java.util.ArrayList;

import org.labs.waiters.Waiter;

public class App {
    public static void main(String[] args) {
        final var waiter = new Waiter();
        final var spoons = new ArrayList<Spoon>();
        for (Integer i = 0; i < Constants.PROGRAMMERS_COUNT; i++) {
            spoons.add(new Spoon(i));
        }

        final var programmers = new ArrayList<Programmer>();
        for (Integer i = 0; i < Constants.PROGRAMMERS_COUNT; i++) {
            Integer leftSpoonIdx = i;
            Integer rightSpoonIdx = i == 0 ? Constants.PROGRAMMERS_COUNT - 1 : i - 1;

            programmers.add(new Programmer(i, waiter, spoons.get(leftSpoonIdx), spoons.get(rightSpoonIdx)));
        }

        for (var p : programmers)
            p.start();

        for (var p : programmers) {
            try {
                p.join();
            } catch (InterruptedException e) {
                System.out.println("Программист " + p.getProgrammerId() + " умер");
            }
        }
    }
}