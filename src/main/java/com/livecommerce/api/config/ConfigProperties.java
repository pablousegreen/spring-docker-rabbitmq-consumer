package com.livecommerce.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class ConfigProperties {
    private String host;
    private String port;
    private String database;
}
