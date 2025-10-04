package org.labs;

import org.labs.restaurant.Restaurant;

public class App {
    public static void main(String[] args) {
        var restaurant = new Restaurant(Constants.PROGRAMMERS_COUNT, Constants.WAITERS_COUNT, Constants.PORTIONS_COUNT);
        restaurant.startDinner();
    }
}