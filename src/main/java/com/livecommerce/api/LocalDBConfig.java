package com.livecommerce.api;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryRef", basePackages = {"com.livecommerce.local.data.dao"})
public class LocalDBConfig {

    @Bean(name="localdatasource")
    @ConfigurationProperties(prefix = "spring.local.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("localdatasource") DataSource dataSource){
        Map<String , Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return builder.dataSource(dataSource).packages("com.livecommerce.local.model")
                .persistenceUnit("Employees").build();
    }
}
