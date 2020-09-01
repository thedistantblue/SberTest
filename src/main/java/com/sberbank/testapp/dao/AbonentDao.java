package com.sberbank.testapp.dao;

import com.sberbank.testapp.entity.Abonent;

import java.util.List;

public interface AbonentDao {
    List<Abonent> findAll();
    Abonent findById(String id);
    int save(Abonent abonent);
    void saveAll(List<Abonent> abonents);
}
