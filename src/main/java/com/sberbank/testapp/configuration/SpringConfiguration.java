package com.sberbank.testapp.configuration;

import com.sberbank.testapp.dao.AbonentDao;
import com.sberbank.testapp.dao.AbonentDaoImpl;
import com.sberbank.testapp.dao.TariffDao;
import com.sberbank.testapp.dao.TariffDaoImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.sberbank.starter")
public class SpringConfiguration {

    @Value("${spring.datasource.driverClassName}")
    private String driverName;
    @Value("${spring.datasource.url}")
    private String url;

    @Bean
    @Qualifier("simepleDataSource")
    public DataSource simpleDataSource() {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver =
                    (Class<? extends Driver>) Class.forName(driverName);
            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            return dataSource;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    @Qualifier("embeddedDataSource")
    public DataSource embeddedDataSource() {
        try {
            EmbeddedDatabaseBuilder embeddedDatabaseBuilder =
                    new EmbeddedDatabaseBuilder();
            return embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2)
                                          .addScripts("classpath:SCHEMA.sql", "classpath:BASE_INFO.sql")
                                          .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("embeddedDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public AbonentDao abonentDao(JdbcTemplate jdbcTemplate, @Qualifier("embeddedDataSource") DataSource dataSource) {
        return new AbonentDaoImpl(jdbcTemplate, dataSource);
    }

    @Bean
    public TariffDao tariffDao(JdbcTemplate jdbcTemplate, @Qualifier("embeddedDataSource") DataSource dataSource) {
        return new TariffDaoImpl(jdbcTemplate, dataSource);
    }

}
