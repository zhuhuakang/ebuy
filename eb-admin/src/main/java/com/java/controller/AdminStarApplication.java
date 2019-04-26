package com.java.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"com.java.controller", "com.java.service.impl"})
@MapperScan(basePackages = {"com.java.mapper"})
@EnableEurekaClient
public class AdminStarApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminStarApplication.class);
    }
}
