package com.sberbank.testapp.dao;

import com.sberbank.testapp.entity.Tariff;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.Map;

public interface TariffDao {
    SqlRowSet findAll();
    Tariff findTariffById(String id);
    int insertTariff(Map<String, Object> dataMap);
}
