package org.labs.loggers;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String... messages) {
        System.out.println(String.join(" ", messages));
    }
}
