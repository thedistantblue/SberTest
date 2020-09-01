package com.sberbank.testapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abonent {
    private Long abonentNumber;
    private String abonentSurname;
    private String abonentFirstname;
    private String abonentSecondname;
    private Date abonentBirth;
    private int abonentTariffNumber;
    private int abonentMinutes;

    @Override
    public String toString() {
        return new StringJoiner("|").add(String.valueOf(abonentNumber))
                                            .add(abonentSurname)
                                            .add(abonentFirstname)
                                            .add(abonentSecondname)
                                            .add(String.valueOf(abonentBirth))
                                            .add(String.valueOf(abonentTariffNumber))
                                            .add(String.valueOf(abonentMinutes))
                                            .toString();
    }
}

