package com.sberbank.testapp.ui;

import com.sberbank.testapp.constants.DatabaseSchema;
import com.sberbank.testapp.entity.Abonent;
import com.sberbank.testapp.entity.Tariff;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class CommandLinePrinter {
    public static void printOptionsMenu() {
        System.out.println("Enter operation number:\n" +
                                   "1 - Query all data from table ABONENT\n" +
                                   "2 - Query all data from table TARIFF\n" +
                                   "3 - Find abonent by number (pre-filled values are 1, 2, 3)\n" +
                                   "4 - Insert abonent data\n" +
                                   "5 - Insert tariff data\n" +
                                   "6 - Generate file in specified directory and load data from it\n" +
                                   "7 - Load data from file\n" +
                                   "type 'exit' to exit the program\n");
    }

    public static void printAbonentTableData(List<Abonent> abonents) {
        System.out.println("ABONENT TABLE");
        System.out.println("NO--SURNAME--FIRSTNAME--SECONDNAME--BIRTH--TARIFF--MINUTES");
        abonents.forEach(abonent -> {
            System.out.println(String.format("%s---%s---%s---%s---%s---%s---%s",
                                             abonent.getAbonentNumber(),
                                             abonent.getAbonentSurname(),
                                             abonent.getAbonentFirstname(),
                                             abonent.getAbonentSecondname(),
                                             abonent.getAbonentBirth(),
                                             abonent.getAbonentTariffNumber(),
                                             abonent.getAbonentMinutes()));
        });
    }

    public static void printTariffTableData(List<Tariff> tariffs) {
        System.out.println("TARIFF TABLE");
        System.out.println("NO--DESCRIPTION");
        tariffs.forEach(tariff -> {
            System.out.println(String.format("%s---%s",
                                             tariff.getTariffNumber(),
                                             tariff.getTariffDescription()));
        });
    }
}
