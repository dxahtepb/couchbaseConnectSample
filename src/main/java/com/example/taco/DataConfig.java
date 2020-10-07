package com.example.taco;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataConfig {
    @Bean
    public DataSource couchbaseDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.intellij.CouchbaseJdbcDriver");
        dataSource.setJdbcUrl("jdbc:couchbase:localhost:11210");
        dataSource.setConnectionTestQuery("select 1;");
        dataSource.addDataSourceProperty("query.scan.consistency", "request_plus");
        dataSource.setUsername("Administrator");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("couchbaseDataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
}
