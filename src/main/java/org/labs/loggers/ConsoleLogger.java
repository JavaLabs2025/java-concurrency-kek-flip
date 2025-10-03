package org.labs.loggers;

public class ConsoleLogger implements Logger {
    private String name;

    public ConsoleLogger(String name) {
        this.name = name;
    }

    @Override
    public void log(String... messages) {
        System.out.println(name + " " + String.join(" ", messages));
    }
}
