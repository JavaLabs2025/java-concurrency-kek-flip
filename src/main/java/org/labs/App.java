package org.labs;

import java.util.ArrayList;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        final var waiter = new Waiter();
        final var spoons = Stream.generate(Spoon::new).limit(Constants.PROGRAMMERS_COUNT).toList();

        final var programmers = new ArrayList<Programmer>();
        for (Integer i = 0; i < Constants.PROGRAMMERS_COUNT; i++) {
            Integer leftSpoonIdx = i == Constants.PROGRAMMERS_COUNT - 1 ? 0 : i;
            Integer rightSpoonIdx = i == 0 ? Constants.PROGRAMMERS_COUNT - 1 : i - 1;

            programmers.add(new Programmer(i, waiter, spoons.get(leftSpoonIdx), spoons.get(rightSpoonIdx)));
        }

        for (var p : programmers)
            p.start();

        for (var p : programmers) {
            try {
                p.join();
            } catch (InterruptedException e) {
            }
        }
    }
}