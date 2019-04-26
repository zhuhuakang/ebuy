package com.java.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka2StarApplication {
    public static void main(String[] args) {
        SpringApplication.run(Eureka2StarApplication.class);
    }
}
