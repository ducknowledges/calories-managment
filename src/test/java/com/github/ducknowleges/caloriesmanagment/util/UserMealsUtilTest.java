package com.github.ducknowleges.caloriesmanagment.util;

import com.github.ducknowleges.caloriesmanagment.model.UserMeal;
import com.github.ducknowleges.caloriesmanagment.model.UserMealWithExcess;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserMealsUtilTest {

    private static List<UserMeal> meals;

    @BeforeAll
    static void setup() {
        meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2022, Month.JANUARY, 30, 10, 0), "Breakfast", 500),
                new UserMeal(LocalDateTime.of(2022, Month.JANUARY, 30, 13, 0), "Lunch", 1000),
                new UserMeal(LocalDateTime.of(2022, Month.JANUARY, 30, 20, 0), "Dinner", 500),
                new UserMeal(LocalDateTime.of(2022, Month.JANUARY, 31, 0, 0), "Food to the limit value", 100),
                new UserMeal(LocalDateTime.of(2022, Month.JANUARY, 31, 10, 0), "Breakfast", 1000),
                new UserMeal(LocalDateTime.of(2022, Month.JANUARY, 31, 13, 0), "Lunch", 500),
                new UserMeal(LocalDateTime.of(2022, Month.JANUARY, 31, 20, 0), "Dinner", 410)
        );
    }

    @Test
    void filteredByCycles() {
        List<UserMealWithExcess> actual = UserMealsUtil.filteredByCaloriesPerDay(
                meals,
                LocalTime.of(7, 0), LocalTime.of(12, 0),
                2000);
        List<UserMealWithExcess> expected = Arrays.asList(
                new UserMealWithExcess(LocalDateTime.of(2022, Month.JANUARY, 30, 10, 0),
                        "Breakfast", 500, false),
                new UserMealWithExcess(LocalDateTime.of(2022, Month.JANUARY, 31, 10, 0),
                        "Breakfast", 1000, true));
        assertThat(actual)
                .hasSize(expected.size())
                .isEqualTo(expected);
    }
}