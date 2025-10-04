package org.labs;

import org.labs.restaurant.Restaurant;

public class App {
    public static void main(String[] args) {
        var restaurant = new Restaurant(Constants.PROGRAMMERS_COUNT, Constants.WAITERS_COUNT, Constants.PORTIONS_COUNT);
        restaurant.startDinner();
        var dinnerInfo = restaurant.getDinnerInfo();

        for (var programmerInfo : dinnerInfo.programmersEatenPortions().entrySet()) {
            System.out.println(new StringBuilder().append("Программист ").append(programmerInfo.getKey())
                    .append(" съел ").append(programmerInfo.getValue()).append(" порций").toString());
        }

        for (var portion : dinnerInfo.waitersServedPortions().entrySet()) {
            System.out.println(new StringBuilder().append("Официант ").append(portion.getKey())
                    .append(" разнес ").append(portion.getValue()).append(" порций").toString());
        }
    }
}