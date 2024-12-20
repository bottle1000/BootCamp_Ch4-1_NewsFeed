package com.example.ch4_1_newsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Ch41NewsFeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch41NewsFeedApplication.class, args);
    }

}
