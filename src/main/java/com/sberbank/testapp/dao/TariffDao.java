package com.sberbank.testapp.dao;

import com.sberbank.testapp.entity.Tariff;

import java.util.List;
import java.util.Map;

public interface TariffDao {
    List<Tariff> findAll();
    Tariff findById(String id);
    void saveAll(List<Tariff> tariffs);
    int save(Tariff tariff);
}
