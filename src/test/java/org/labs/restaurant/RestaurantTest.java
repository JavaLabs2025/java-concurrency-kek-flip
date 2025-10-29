package org.labs.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.labs.TestConstants;

public class RestaurantTest {
    private Restaurant restaurant;
    private DinnerInfo dinnerInfo;

    @BeforeEach
    void setup() {
        restaurant = new Restaurant(TestConstants.PROGRAMMERS_COUNT, TestConstants.WAITERS_COUNT,
                TestConstants.PORTIONS_COUNT);
        restaurant.startDinner();
        dinnerInfo = restaurant.getDinnerInfo();
    }

    @RepeatedTest(value = 5)
    void getDinnerInfo_waitersServedAllPortions() {
        var serverPortions = dinnerInfo.waitersServedPortions().entrySet().stream().map(Entry::getValue)
                .reduce(0, Integer::sum);

        assertEquals(TestConstants.PORTIONS_COUNT, serverPortions, "Официанты разнесли все порции");
    }

    @RepeatedTest(value = 5)
    void getDinnerInfo_waitersServedPortionsAverageFairness() {
        var expectedAverage = TestConstants.PORTIONS_COUNT.floatValue() / TestConstants.WAITERS_COUNT;
        var actualAverage = dinnerInfo.waitersServedPortions().entrySet().stream().mapToInt(Entry::getValue)
                .average();

        assertTrue(actualAverage.isPresent());
        assertEquals(expectedAverage, actualAverage.getAsDouble(),
                TestConstants.AVERAGE_DELTA_PERCENT * expectedAverage,
                "Среднее кол-во разнесенных официантом порций не отклоняется от ожидаемого более чем на "
                        + TestConstants.AVERAGE_DELTA_PERCENT * 100 + "%");
    }

    @RepeatedTest(value = 5)
    void getDinnerInfo_waitersServedPortionsMaxFairness() {
        var expectedAverage = TestConstants.PORTIONS_COUNT / TestConstants.WAITERS_COUNT;
        var actualMax = dinnerInfo.waitersServedPortions().entrySet().stream().mapToInt(Entry::getValue)
                .max();

        assertTrue(actualMax.isPresent());
        assertTrue(Math.abs(expectedAverage - actualMax.getAsInt()) < TestConstants.DIFF_PERCENT * expectedAverage,
                "Максимальное кол-во разнесенных официантом порций не отклоняется от ожидаемого более чем на "
                        + TestConstants.DIFF_PERCENT * 100 + "%");
    }

    @RepeatedTest(value = 5)
    void getDinnerInfo_waitersServedPortionsMinFairness() {
        var expectedAverage = TestConstants.PORTIONS_COUNT / TestConstants.WAITERS_COUNT;
        var actualMin = dinnerInfo.waitersServedPortions().entrySet().stream().mapToInt(Entry::getValue)
                .min();

        assertTrue(actualMin.isPresent());
        assertTrue(Math.abs(expectedAverage - actualMin.getAsInt()) < TestConstants.DIFF_PERCENT * expectedAverage,
                "Минимальное кол-во разнесенных официантом порций не отклоняется от ожидаемого более чем на "
                        + TestConstants.DIFF_PERCENT * 100 + "%");
    }

    @RepeatedTest(value = 5)
    void getDinnerInfo_programmersEatenAllPortions() {
        var eatenPortions = dinnerInfo.programmersEatenPortions().entrySet().stream().mapToInt(Entry::getValue)
                .sum();

        assertEquals(TestConstants.PORTIONS_COUNT, eatenPortions, "Программисты съели все порции");
    }

    @RepeatedTest(value = 5)
    void getDinnerInfo_programmersEatenPortionAverageFairness() {
        var expectedAverage = TestConstants.PORTIONS_COUNT.floatValue() / TestConstants.PROGRAMMERS_COUNT;
        var actualAverage = dinnerInfo.programmersEatenPortions().entrySet().stream().mapToInt(Entry::getValue)
                .average();

        assertTrue(actualAverage.isPresent());
        assertEquals(expectedAverage, actualAverage.getAsDouble(),
                TestConstants.AVERAGE_DELTA_PERCENT * expectedAverage,
                "Среднее кол-во съеденных порций не отклоняется от ожидаемого более чем на "
                        + TestConstants.AVERAGE_DELTA_PERCENT * 100 + "%");
    }

    @RepeatedTest(value = 5)
    void getDinnerInfo_programmersEatenPortionMaxFairness() {
        var expectedAverage = TestConstants.PORTIONS_COUNT / TestConstants.PROGRAMMERS_COUNT;
        var actualMax = dinnerInfo.programmersEatenPortions().entrySet().stream().mapToInt(Entry::getValue)
                .max();

        assertTrue(actualMax.isPresent());
        assertTrue(Math.abs(expectedAverage - actualMax.getAsInt()) < TestConstants.DIFF_PERCENT * expectedAverage,
                "Максимальное кол-во съеденных программистом порций не отклоняется от ожидаемого более чем на "
                        + TestConstants.DIFF_PERCENT * 100 + "%");
    }

    @RepeatedTest(value = 5)
    void getDinnerInfo_programmersEatenPortionMinFairness() {
        var expectedAverage = TestConstants.PORTIONS_COUNT / TestConstants.PROGRAMMERS_COUNT;
        var actualMin = dinnerInfo.programmersEatenPortions().entrySet().stream().mapToInt(Entry::getValue)
                .min();

        assertTrue(actualMin.isPresent());
        assertTrue(Math.abs(expectedAverage - actualMin.getAsInt()) < TestConstants.DIFF_PERCENT * expectedAverage,
                "Минимальное кол-во съеденных программистом порций не отклоняется от ожидаемого более чем на "
                        + TestConstants.DIFF_PERCENT * 100 + "%");
    }
}
