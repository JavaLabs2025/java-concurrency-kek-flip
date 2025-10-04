package org.labs.restaurant;

import java.util.Map;

public record DinnerInfo(Map<Integer, Integer> programmersEatenPortions, Map<Integer, Integer> waitersServedPortions) {
}
