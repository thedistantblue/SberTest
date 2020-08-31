package com.sberbank.testapp.ui;

import com.sberbank.testapp.constants.ColumnConstants;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class CommandLinePrinter {
    public static void printOptionsMenu() {
        System.out.println("Enter operation number:\n" +
                                   "1 - Query all data from table ABONENT\n" +
                                   "2 - Query all data from table TARIFF\n" +
                                   "3 - Find abonent by number (pre-filled values are 1, 2, 3)\n" +
                                   "4 - Insert abonent data\n" +
                                   "5 - Generate file in specified directory and load data from it\n" +
                                   "6 - Load data from file\n" +
                                   "type 'exit' to exit the program\n");
    }

    public static void printAbonentTableData(SqlRowSet rowSet) {
        System.out.println("ABONENT TABLE");
        System.out.println("NO--SURNAME--FIRSTNAME--SECONDNAME--BIRTH--TARIFF--MINUTES");
        while (rowSet.next()) {
            System.out.println(String.format("%s---%s---%s---%s---%s---%s---%s",
                                             rowSet.getString(ColumnConstants.Abonent.ID_COLUMN),
                                             rowSet.getString(ColumnConstants.Abonent.SURNAME_COLUMN),
                                             rowSet.getString(ColumnConstants.Abonent.FIRSTNAME_COLUMN),
                                             rowSet.getString(ColumnConstants.Abonent.SECONDNAME_COLUMN),
                                             rowSet.getString(ColumnConstants.Abonent.BIRTH_COLUMN),
                                             rowSet.getString(ColumnConstants.Abonent.TARIFF_COLUMN),
                                             rowSet.getString(ColumnConstants.Abonent.MINUTES_COLUMN)));
        }
    }

    public static void printTariffTableData(SqlRowSet rowSet) {
        System.out.println("TARIFF TABLE");
        System.out.println("NO--DESCRIPTION");
        while (rowSet.next()) {
            System.out.println(String.format("%s---%s",
                                             rowSet.getString(ColumnConstants.Tariff.ID_COLUMN),
                                             rowSet.getString(ColumnConstants.Tariff.DESCRIPTION_COLUMN)));
        }
    }
}
