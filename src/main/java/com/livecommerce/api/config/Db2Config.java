package com.livecommerce.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
public class Db2Config {
    protected static final String MONGO_TEMPLATE = "db2MongoTemplate";
}
