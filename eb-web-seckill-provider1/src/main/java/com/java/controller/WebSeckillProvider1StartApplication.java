package com.java.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.java.*")
@MapperScan(basePackages = "com.java.mapper")
@EnableDiscoveryClient
public class WebSeckillProvider1StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSeckillProvider1StartApplication.class);
    }
}
