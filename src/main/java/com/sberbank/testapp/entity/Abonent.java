package com.sberbank.testapp.entity;

import java.util.Date;
import java.util.StringJoiner;

public class Abonent {
    private Long abonentNumber;
    private String abonentSurname;
    private String abonentFirstname;
    private String abonentSecondname;
    private Date abonentBirth;
    private int abonentTariffNumber;
    private int abonentMinutes;

    public Long getAbonentNumber() {
        return abonentNumber;
    }

    public void setAbonentNumber(Long abonentNumber) {
        this.abonentNumber = abonentNumber;
    }

    public String getAbonentSurname() {
        return abonentSurname;
    }

    public void setAbonentSurname(String abonentSurname) {
        this.abonentSurname = abonentSurname;
    }

    public String getAbonentFirstname() {
        return abonentFirstname;
    }

    public void setAbonentFirstname(String abonentFirstname) {
        this.abonentFirstname = abonentFirstname;
    }

    public String getAbonentSecondname() {
        return abonentSecondname;
    }

    public void setAbonentSecondname(String abonentSecondname) {
        this.abonentSecondname = abonentSecondname;
    }

    public Date getAbonentBirth() {
        return abonentBirth;
    }

    public void setAbonentBirth(Date abonentBirth) {
        this.abonentBirth = abonentBirth;
    }

    public int getAbonentTariffNumber() {
        return abonentTariffNumber;
    }

    public void setAbonentTariffNumber(int abonentTariffNumber) {
        this.abonentTariffNumber = abonentTariffNumber;
    }

    public int getAbonentMinutes() {
        return abonentMinutes;
    }

    public void setAbonentMinutes(int abonentMinutes) {
        this.abonentMinutes = abonentMinutes;
    }

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
