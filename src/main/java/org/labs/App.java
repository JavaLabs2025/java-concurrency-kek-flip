package org.labs;

import java.util.ArrayList;

import org.labs.programmer.Programmer;
import org.labs.resources.Spoon;
import org.labs.resources.Waiter;

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
            Integer rightSpoonIdx = (i + 1) % spoons.size();

            programmers.add(new Programmer(i, waiter, spoons.get(leftSpoonIdx), spoons.get(rightSpoonIdx)));
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
    }
}