package com.sberbank.test;

import com.sberbank.testapp.configuration.SpringConfiguration;
import com.sberbank.testapp.dao.AbonentDao;
import com.sberbank.testapp.dao.TariffDao;
import com.sberbank.testapp.entity.Abonent;
import com.sberbank.testapp.entity.Tariff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class DatabaseOperationsTest {

    ApplicationContext context =
            new AnnotationConfigApplicationContext(SpringConfiguration.class);

    AbonentDao abonentDao = context.getBean(AbonentDao.class);
    TariffDao tariffDao = context.getBean(TariffDao.class);

    @Test
    public void testDao() {
        Assertions.assertEquals(3, abonentDao.findAll().size());
        Assertions.assertEquals(3, tariffDao.findAll().size());

        abonentDao.save(new Abonent(1L,
                                    "surename",
                                    "firstname",
                                    "secondname",
                                    java.sql.Date.valueOf(LocalDate.parse("1990-11-25")),
                                    3,
                                    123123));

        tariffDao.save(new Tariff(10,
                                  "description10"));

        Assertions.assertEquals(4, abonentDao.findAll().size());
        Assertions.assertEquals(4, tariffDao.findAll().size());
    }

    @Test
    public void testDatabaseConstraints() {
        int id = abonentDao.save(new Abonent(1L,
                                             "surename",
                                             "firstname",
                                             "secondname",
                                             java.sql.Date.valueOf(LocalDate.parse("1990-11-25")),
                                             12,
                                             123123));
        Assertions.assertEquals(-1, id);
    }
}
