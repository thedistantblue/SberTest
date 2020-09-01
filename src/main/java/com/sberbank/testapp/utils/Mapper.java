package com.sberbank.testapp.utils;

import com.sberbank.testapp.entity.Abonent;
import com.sberbank.testapp.entity.Tariff;

import java.time.LocalDate;

public class Mapper {
    public static Abonent mapToAbonentWithoutId(String[] line) {
        Abonent abonent = new Abonent();
        abonent.setAbonentSurname(line[0].trim());
        abonent.setAbonentFirstname(line[1].trim());
        abonent.setAbonentSecondname(line[2].trim());
        abonent.setAbonentBirth(java.sql.Date.valueOf(LocalDate.parse(line[3].trim())));
        abonent.setAbonentTariffNumber(Integer.parseInt(line[4].trim()));
        abonent.setAbonentMinutes(Integer.parseInt(line[5].trim()));
        return abonent;
    }

    public static Tariff mapToTariffWithoutId(String line) {
        Tariff tariff = new Tariff();
        tariff.setTariffDescription(line);
        return tariff;
    }
}
