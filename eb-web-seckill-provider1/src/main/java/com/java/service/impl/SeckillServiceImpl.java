package com.java.service.impl;

import com.java.mapper.SeckillMapper;
import com.java.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillMapper seckillMapper;

    @Override
    public void doSeckill(Integer productId) {
        List<Map<String, Object>> seckillList = seckillMapper.findSeckillProduct();

    }
}
