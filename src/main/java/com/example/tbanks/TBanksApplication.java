package com.example.tbanks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TBanksApplication {

    public static void main(String[] args) {
        SpringApplication.run(TBanksApplication.class, args);
    }

}