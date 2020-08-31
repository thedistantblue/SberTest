package com.sberbank.testapp.dao;

import com.sberbank.testapp.entity.Tariff;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Component
public class TariffDaoImpl implements TariffDao {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    private final SimpleJdbcInsert jdbcInsert;

    public TariffDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("tariff").usingGeneratedKeyColumns("ID");
    }

    @Override
    public SqlRowSet findAll() {
        return jdbcTemplate.queryForRowSet("select * from tariff");
    }

    @Override
    public Tariff findTariffById(String id) {
        return null;
    }

    @Override
    public int insertTariff(Map<String, Object> dataMap) {
        return jdbcInsert.executeAndReturnKey(dataMap).intValue();
    }


}
