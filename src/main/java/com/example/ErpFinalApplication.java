package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.example.model","com.example.controller"})
public class ErpFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpFinalApplication.class, args);
    }

}
