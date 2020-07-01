package ru.javawebinar.topjava;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_MEAL_SEQ;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    //    public static final int NOT_FOUND = 10;
    public static final int WATER_ID = START_MEAL_SEQ;
    public static final int SODA_ID = START_MEAL_SEQ + 1;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final Meal WATER = new Meal(WATER_ID, LocalDateTime.now(), "water", 0);
    public static final Meal SODA = new Meal(SODA_ID, LocalDateTime.now(), "soda", 200);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "New", 0);
    }

    public static Meal getUpdated() {
        Meal updated = WATER;
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "userid");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("userid").isEqualTo(expected);
    }
}
