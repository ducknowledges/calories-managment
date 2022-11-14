package com.github.ducknowleges.caloriesmanagment.util;

import com.github.ducknowleges.caloriesmanagment.model.UserMeal;
import com.github.ducknowleges.caloriesmanagment.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {

    public static List<UserMealWithExcess> filteredByCaloriesPerDay(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapOfCaloriesPerDay = getMapOfCaloriesPerDay(meals);
        List<UserMealWithExcess> mealsWithExcess = new ArrayList<>();
        for (UserMeal meal: meals) {
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                LocalDate localDate = LocalDate.of(
                        meal.getDateTime().getYear(),
                        meal.getDateTime().getMonth(),
                        meal.getDateTime().getDayOfMonth());
                mealsWithExcess.add(
                           new UserMealWithExcess(
                                meal.getDateTime(),
                                meal.getDescription(),
                                meal.getCalories(),
                                mapOfCaloriesPerDay.get(localDate) > caloriesPerDay )
                );
            }
        }
        return mealsWithExcess;
    }

    private static Map<LocalDate, Integer> getMapOfCaloriesPerDay(List<UserMeal> meals) {
        Map<LocalDate, Integer> map = new HashMap<>();
        for (UserMeal meal: meals) {
            LocalDateTime dateTime = meal.getDateTime();
            LocalDate date = LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
            map.merge(date, meal.getCalories(), Integer::sum);
        }
        return map;
    }
}
