package com.sberbank.testapp.dao;

import com.sberbank.testapp.constants.DatabaseSchema;
import com.sberbank.testapp.entity.Tariff;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TariffDaoImpl implements TariffDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public TariffDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(DatabaseSchema.Tariff.NAME)
                                                          .usingGeneratedKeyColumns(DatabaseSchema.Tariff.Columns.ID);
    }

    @Override
    public List<Tariff> findAll() {
        List<Tariff> tariffs = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from tariff");
        while (rowSet.next()) {
            tariffs.add(mapToTariff(rowSet));
        }
        return tariffs;
    }

    @Override
    public Tariff findById(String id) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from tariff where id = " + id);
        while (rowSet.next()) {
            return mapToTariff(rowSet);
        }
        return null;
    }

    @Override
    public void saveAll(List<Tariff> tariffs) {
        tariffs.forEach(tariff -> {
            jdbcInsert.execute(mapToDataMap(tariff));
        });
    }

    @Override
    public int save(Tariff tariff) {
        return jdbcInsert.executeAndReturnKey(mapToDataMap(tariff))
                         .intValue();
    }

    private Map<String, Object> mapToDataMap(Tariff tariff) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(DatabaseSchema.Tariff.Columns.DESCRIPTION, tariff.getTariffDescription());
        return dataMap;
    }

    private Tariff mapToTariff(SqlRowSet rowSet) {
        Tariff tariff = new Tariff();
        tariff.setTariffNumber(Long.parseLong(rowSet.getString(DatabaseSchema.Tariff.Columns.ID)));
        tariff.setTariffDescription(rowSet.getString(DatabaseSchema.Tariff.Columns.DESCRIPTION));
        return tariff;
    }

}
