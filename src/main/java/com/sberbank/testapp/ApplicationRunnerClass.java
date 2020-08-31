package com.sberbank.testapp;

import com.sberbank.generator.DataFileOperations;
import com.sberbank.testapp.configuration.SpringConfiguration;
import com.sberbank.testapp.constants.ColumnConstants;
import com.sberbank.testapp.dao.AbonentDao;
import com.sberbank.testapp.dao.TariffDao;
import com.sberbank.testapp.entity.Abonent;
import com.sberbank.testapp.ui.CommandLinePrinter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.*;
import java.util.*;

public class ApplicationRunnerClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfiguration.class);

        AbonentDao abonentDao = context.getBean(AbonentDao.class);
        TariffDao tariffDao = context.getBean(TariffDao.class);

        try {

            while (true) {
                CommandLinePrinter.printOptionsMenu();

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                switch (br.readLine()) {
                    case "exit": return;
                    case "1":  {
                        SqlRowSet rowSet = abonentDao.findAll();
                        CommandLinePrinter.printAbonentTableData(rowSet);
                    }
                    break;
                    case "2": {
                        SqlRowSet rowSet = tariffDao.findAll();
                        CommandLinePrinter.printTariffTableData(rowSet);
                    }
                    break;
                    case "4": {
                        System.out.println("Data format : surname, firstname, secondname, birth, tariff, minutes");
                        List<String> data = Arrays.asList(br.readLine().split(","));
                        Map<String, Object> dataMap = new HashMap<>();
                        dataMap.put(ColumnConstants.Abonent.SURNAME_COLUMN, data.get(0));
                        dataMap.put(ColumnConstants.Abonent.FIRSTNAME_COLUMN, data.get(1));
                        dataMap.put(ColumnConstants.Abonent.SECONDNAME_COLUMN, data.get(2));
                        dataMap.put(ColumnConstants.Abonent.BIRTH_COLUMN, data.get(3));
                        dataMap.put(ColumnConstants.Abonent.TARIFF_COLUMN, data.get(4));
                        dataMap.put(ColumnConstants.Abonent.MINUTES_COLUMN, data.get(5));

                        int id = abonentDao.insertAbonent(dataMap);
                        if (id == -1) {
                            System.out.println("Failed to add abonent, exception above");
                        } else {
                            System.out.println("Abonent added, id: " + id);
                        }
                    }
                    break;
                    case "5": {
                        System.out.println("Enter directory:\n");
                        File path = new File(br.readLine());
                        String filePath = DataFileOperations.generate(path);
                        List<Abonent> abonents = DataFileOperations.parseFile(filePath);

                        System.out.println("Abonents parsed: " + abonents.size());

                        abonents.forEach(abonent -> {
                            System.out.println(abonent.toString());
                        });

                        abonentDao.saveAll(abonents);
                        System.out.println("Abonents saved: " + abonents.size());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
