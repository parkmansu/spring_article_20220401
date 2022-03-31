package com.sparta.chapter3_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Chapter31Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter31Application.class, args);
    }

}
