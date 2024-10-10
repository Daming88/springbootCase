package com.daming.liquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "test1DataSource")
    @ConfigurationProperties("spring.datasource.dynamic.datasource.test1")
    public DataSource test1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test1LiquibaseProperties")
    @ConfigurationProperties("spring.datasource.dynamic.datasource.test1.liquibase")
    public LiquibaseProperties test1LiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(name = "test1liquibase")
    public SpringLiquibase test1Liquibase() {
        return createSpringLiquibase(test1DataSource(), test1LiquibaseProperties());
    }

    private SpringLiquibase createSpringLiquibase(DataSource ds, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(ds);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }


    @Bean(name = "test2DataSource")
    @ConfigurationProperties("spring.datasource.dynamic.datasource.test2")
    public DataSource test2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test2LiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.test2.liquibase")
    public LiquibaseProperties test2LiquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(name = "test2Liquibase")
    public SpringLiquibase test2Liquibase() {
        return createSpringLiquibase(test2DataSource(), test2LiquibaseProperties());
    }
}
