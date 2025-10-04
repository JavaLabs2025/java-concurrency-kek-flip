package org.labs.logger;

public class ConsoleLogger implements Logger {
    private String name;

    public ConsoleLogger() {
        this("");
    }

    public ConsoleLogger(String name) {
        this.name = name;
    }

    @Override
    public void log(String... messages) {
        final var prefix = !name.isEmpty() ? name + " " : "";
        System.out.println(prefix + String.join(" ", messages));
    }
}
