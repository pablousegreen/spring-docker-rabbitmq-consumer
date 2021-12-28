package com.livecommerce.api.config;

import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.livecommerce.data.dao"},
        mongoTemplateRef = Db1Config.MONGO_TEMPLATE)
public class MultipleMongoConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.db1")
    public MongoProperties getDb1Props() throws Exception {
        return new MongoProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.db2")
    public MongoProperties getDb2Props() throws Exception {
        return new MongoProperties();
    }

    @Primary
    @Bean(name = "db1MongoTemplate")
    public MongoTemplate db1MongoTemplate() throws Exception {
        return new MongoTemplate(db1MongoDatabaseFactory(getDb1Props()));
    }

    @Bean(name ="db2MongoTemplate")
    public MongoTemplate db2MongoTemplate() throws Exception {
        return new MongoTemplate(db2MongoDatabaseFactory(getDb2Props()));
    }

    @Primary
    @Bean
    public MongoDatabaseFactory db1MongoDatabaseFactory(MongoProperties mongoProperties) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongoProperties.getUri()
        );
    }

    @Bean
    public MongoDatabaseFactory db2MongoDatabaseFactory(MongoProperties mongoProperties) throws Exception {
        return new SimpleMongoClientDatabaseFactory(
                mongoProperties.getUri()
        );
    }
}
