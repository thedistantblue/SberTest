package com.sberbank.testapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.StringJoiner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tariff {
    private long tariffNumber;
    private String tariffDescription;

    @Override
    public String toString() {
        return new StringJoiner("|").add(String.valueOf(tariffNumber))
                                            .add(tariffDescription)
                                            .toString();
    }

}
