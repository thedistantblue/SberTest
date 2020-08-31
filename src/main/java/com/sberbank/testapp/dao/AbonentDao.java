package com.sberbank.testapp.dao;

import com.sberbank.testapp.entity.Abonent;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;
import java.util.Map;

public interface AbonentDao {
    SqlRowSet findAll();
    Abonent findAbonentById(String id);
    int insertAbonent(Map<String, Object> dataMap);
    void saveAll(List<Abonent> abonents);
}
