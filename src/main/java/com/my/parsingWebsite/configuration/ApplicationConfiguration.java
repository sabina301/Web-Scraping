package com.my.parsingWebsite.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.my.parsingWebsite.repository")
@EntityScan("com.my.parsingWebsite.entity")
public class ApplicationConfiguration {
}
