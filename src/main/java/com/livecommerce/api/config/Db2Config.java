package com.livecommerce.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.livecommerce.data.dao2"},
        mongoTemplateRef = Db2Config.MONGO_TEMPLATE
)
public class Db2Config {
    protected static final String MONGO_TEMPLATE = "db2MongoTemplate";
}
