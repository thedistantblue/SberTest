package com.sberbank.testapp.entity;

public class Tariff {
    private long tariffNumber;
    private String tariffDescription;

    public long getTariffNumber() {
        return tariffNumber;
    }

    public void setTariffNumber(long tariffNumber) {
        this.tariffNumber = tariffNumber;
    }

    public String getTariffDescription() {
        return tariffDescription;
    }

    public void setTariffDescription(String tariffDescription) {
        this.tariffDescription = tariffDescription;
    }
}
