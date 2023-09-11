package com.sparta.week5project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week5projectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week5projectApplication.class, args);
    }

}
