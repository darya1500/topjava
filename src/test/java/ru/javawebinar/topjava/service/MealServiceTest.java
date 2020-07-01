package ru.javawebinar.topjava.service;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import java.util.List;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
//перед каждым тестом восстановить базу данных
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest extends TestCase {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private MealRepository repository;

    public void testGet() {
        Meal meal = service.get(WATER_ID, USER_ID);
        assertMatch(meal,WATER);
    }

    public void testDelete() {
        service.delete(WATER_ID,USER_ID);
        assertNull(repository.get(WATER_ID,USER_ID));
    }

    public void testGetBetweenInclusive() {
    }

    public void testGetAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, WATER, SODA);
    }

    public void testUpdate() {
        Meal updated = getUpdated();
        service.update(updated,USER_ID);
        assertMatch(service.get(WATER_ID,USER_ID), updated);
    }

    public void testCreate() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId,USER_ID), newMeal);
    }
}