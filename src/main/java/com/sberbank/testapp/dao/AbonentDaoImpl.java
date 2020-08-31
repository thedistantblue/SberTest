package com.sberbank.testapp.dao;

import com.sberbank.testapp.constants.ColumnConstants;
import com.sberbank.testapp.entity.Abonent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AbonentDaoImpl implements AbonentDao {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    private final SimpleJdbcInsert jdbcInsert;

    public AbonentDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("abonent").usingGeneratedKeyColumns("ID");
    }

    @Override
    public SqlRowSet findAll() {
        return jdbcTemplate.queryForRowSet("select * from abonent");
    }

    @Override
    public Abonent findAbonentById(String id) {
        return null;
    }

    @Override
    public int insertAbonent(Map<String, Object> dataMap) {
        try {
            return jdbcInsert.executeAndReturnKey(dataMap).intValue();
        } catch (DataIntegrityViolationException e) {
            System.out.println("No such tariff number, you have to add tariff first");
            return -1;
        }
    }

    @Override
    public void saveAll(List<Abonent> abonents) {
        abonents.forEach(abonent -> {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put(ColumnConstants.Abonent.SURNAME_COLUMN, abonent.getAbonentSurname());
            dataMap.put(ColumnConstants.Abonent.FIRSTNAME_COLUMN, abonent.getAbonentFirstname());
            dataMap.put(ColumnConstants.Abonent.SECONDNAME_COLUMN, abonent.getAbonentSecondname());
            dataMap.put(ColumnConstants.Abonent.BIRTH_COLUMN, abonent.getAbonentBirth());
            dataMap.put(ColumnConstants.Abonent.TARIFF_COLUMN, abonent.getAbonentTariffNumber());
            dataMap.put(ColumnConstants.Abonent.MINUTES_COLUMN, abonent.getAbonentMinutes());
            jdbcInsert.execute(dataMap);
        });
    }
}
