package com.java.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka1StarAppliction {
    public static void main(String[] args) {
        SpringApplication.run(Eureka1StarAppliction.class);
    }
}
