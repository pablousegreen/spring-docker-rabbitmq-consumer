package com.livecommerce.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.livecommerce.data.dao"},
        mongoTemplateRef = Db1Config.MONGO_TEMPLATE
)
public class Db1Config {
    protected static final String MONGO_TEMPLATE = "db1MongoTemplate";
}
