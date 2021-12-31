package com.livecommerce.api.config;

import com.livecommerce.data.dao.EmployeesRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Slf4j
@Configuration
@EnableMongoRepositories(basePackageClasses = {EmployeesRepository.class},
        mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {

    @Primary
    @Bean
        @ConfigurationProperties(prefix = "spring.data.mongodb.db1")
    public MongoProperties getDbOneProps() throws Exception {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception {
        return new MongoTemplate(dbOneMongoDatabaseFactory(getDbOneProps()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory dbOneMongoDatabaseFactory(MongoProperties mongoProperties) throws Exception {
        log.info("$$$ {} ",getDbOneProps().getUri());
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

}
