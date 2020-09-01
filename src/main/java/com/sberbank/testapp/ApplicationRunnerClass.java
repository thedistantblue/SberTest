package com.sberbank.testapp;

import com.sberbank.generator.DataFileOperations;
import com.sberbank.testapp.configuration.SpringConfiguration;
import com.sberbank.testapp.dao.AbonentDao;
import com.sberbank.testapp.dao.TariffDao;
import com.sberbank.testapp.entity.Abonent;
import com.sberbank.testapp.entity.Tariff;
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
                        List<Abonent> abonents = abonentDao.findAll();
                        CommandLinePrinter.printAbonentTableData(abonents);
                    }
                    break;
                    case "2": {
                        List<Tariff> rowSet = tariffDao.findAll();
                        CommandLinePrinter.printTariffTableData(rowSet);
                    }
                    break;
                    case "3": {
                        System.out.println("Enter id: ");
                        String id = br.readLine();
                        Abonent abonent = abonentDao.findById(id);
                        System.out.println(abonent.toString());
                    }
                    break;
                    case "4": {
                        System.out.println("Data format : surname, firstname, secondname, birth, tariff, minutes");
                        Abonent abonent = DataFileOperations.mapToAbonentWithoutId(br.readLine().split(","));

                        int id = abonentDao.save(abonent);
                        if (id == -1) {
                            System.out.println("Failed to add abonent, exception above");
                        } else {
                            System.out.println("Abonent added, id: " + id);
                        }
                    }
                    break;
                    case "5": {
                        System.out.println("Data format : description");
                        Tariff tariff = DataFileOperations.mapToTariffWithoutId(br.readLine());
                        int id = tariffDao.save(tariff);
                        System.out.println("Tariff added, id: " + id);
                    }
                    case "6": {
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
