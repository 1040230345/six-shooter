package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SixShooterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SixShooterApplication.class, args);
    }

}

