package com.sberbank.testapp.dao;

import com.sberbank.testapp.constants.DatabaseSchema;
import com.sberbank.testapp.entity.Abonent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AbonentDaoImpl implements AbonentDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public AbonentDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(DatabaseSchema.Abonent.NAME)
                                                          .usingGeneratedKeyColumns(DatabaseSchema.Abonent.Columns.ID);
    }

    @Override
    public List<Abonent> findAll() {
        List<Abonent> abonents = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from abonent");
        while (rowSet.next()) {
            abonents.add(mapToAbonent(rowSet));
        }
        return abonents;
    }

    @Override
    public Abonent findById(String id) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from abonent where id = " + id);
        while (rowSet.next()) {
            return mapToAbonent(rowSet);
        }
        return null;
    }

    @Override
    public int save(Abonent abonent) {
        try {
            Map<String, Object> dataMap = mapToDataMap(abonent);
            int id = jdbcInsert.executeAndReturnKey(dataMap)
                               .intValue();
            abonent.setAbonentNumber(Integer.toUnsignedLong(id));
            return id;
        } catch (DataIntegrityViolationException e) {
            System.out.println("No such tariff number, you have to add tariff first");
            return -1;
        }
    }

    @Override
    public void saveAll(List<Abonent> abonents) {
        abonents.forEach(abonent -> {
            jdbcInsert.execute(mapToDataMap(abonent));
        });
    }

    private Map<String, Object> mapToDataMap(Abonent abonent) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(DatabaseSchema.Abonent.Columns.SURNAME, abonent.getAbonentSurname());
        dataMap.put(DatabaseSchema.Abonent.Columns.FIRSTNAME, abonent.getAbonentFirstname());
        dataMap.put(DatabaseSchema.Abonent.Columns.SECONDNAME, abonent.getAbonentSecondname());
        dataMap.put(DatabaseSchema.Abonent.Columns.BIRTH, abonent.getAbonentBirth());
        dataMap.put(DatabaseSchema.Abonent.Columns.TARIFF, abonent.getAbonentTariffNumber());
        dataMap.put(DatabaseSchema.Abonent.Columns.MINUTES, abonent.getAbonentMinutes());
        return dataMap;
    }

    private Abonent mapToAbonent(SqlRowSet rowSet) {
        Abonent abonent = new Abonent();
        abonent.setAbonentNumber(Long.parseLong(rowSet.getString(DatabaseSchema.Abonent.Columns.ID)));
        abonent.setAbonentSurname(rowSet.getString(DatabaseSchema.Abonent.Columns.SURNAME));
        abonent.setAbonentFirstname(rowSet.getString(DatabaseSchema.Abonent.Columns.FIRSTNAME));
        abonent.setAbonentSecondname(rowSet.getString(DatabaseSchema.Abonent.Columns.SECONDNAME));
        abonent.setAbonentBirth(Date.valueOf(LocalDate.parse(rowSet.getString(DatabaseSchema.Abonent.Columns.BIRTH))));
        abonent.setAbonentTariffNumber(Integer.parseInt(rowSet.getString(DatabaseSchema.Abonent.Columns.TARIFF)));
        abonent.setAbonentMinutes(Integer.parseInt(rowSet.getString(DatabaseSchema.Abonent.Columns.MINUTES)));
        return abonent;
    }
}
