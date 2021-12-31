package com.livecommerce.api.config;

import com.livecommerce.data.dao2.EmployeesTwoRepository;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = {EmployeesTwoRepository.class},
        mongoTemplateRef = "secondaryMongoTemplate")
public class SecundaryMongoConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.db2")
    public MongoProperties getDbTwoProps() throws Exception {
        return new MongoProperties();
    }

    @Bean(name = "secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(dbTwoMongoDatabaseFactory(getDbTwoProps()));
    }

    @Bean
    public MongoDatabaseFactory dbTwoMongoDatabaseFactory(MongoProperties mongoProperties) throws Exception {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }
}
