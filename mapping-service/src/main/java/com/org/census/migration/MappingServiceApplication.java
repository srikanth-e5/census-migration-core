package com.org.census.migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MappingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}