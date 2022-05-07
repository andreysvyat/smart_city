package ru.krd.smc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "ru.krd.smc.model.entity")
@EnableJpaRepositories(basePackages = "ru.krd.smc.dba")
public class DBConfig {
}
