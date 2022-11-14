package com.github.ducknowleges.caloriesmanagment.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;

class TimeUtilTest {

    private static LocalTime startTime;
    private static LocalTime endTime;

    @BeforeAll
    static void setup() {
        startTime = LocalTime.of(7, 0);
        endTime = LocalTime.of(13, 0);
    }

    @Test
    void returnTrueIfItIsBetweenHalfOpen() {
        LocalTime lt = LocalTime.of(7, 0);
        assertThat(TimeUtil.isBetweenHalfOpen(lt, startTime, endTime)).isTrue();
    }

    @Test
    void returnFalseIfItIsNotBetweenHalfOpen() {
        LocalTime lt = LocalTime.of(13, 0);
        assertThat(TimeUtil.isBetweenHalfOpen(lt, startTime, endTime)).isFalse();
    }
}