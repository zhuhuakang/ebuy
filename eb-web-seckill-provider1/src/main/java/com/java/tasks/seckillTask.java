package com.java.tasks;

import com.java.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class seckillTask {

    @Autowired
    private SeckillService seckillService;

    @Scheduled(cron = "0/5 * * * * *")
    public void seckillTask1() {

    }
}
