package com.java.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * description：
 * author：丁鹏
 * date：09:27
 */
@SpringBootApplication(scanBasePackages = {"com.java.controller", "com.java.service.impl", "com.java.tasks"})
@MapperScan(basePackages = "com.java.mapper")
@EnableCaching//开启缓存
@EnableEurekaClient
public class WebStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebStartApplication.class);
    }
}
