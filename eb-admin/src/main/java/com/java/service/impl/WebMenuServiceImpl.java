package com.java.service.impl;

import com.java.mapper.WebMenuMapper;
import com.java.service.WebMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebMenuServiceImpl implements WebMenuService {
    @Autowired
    private WebMenuMapper webMenuMapper;

    /**
     * 分页查询前台横向导航栏数据
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public Map<String, Object> findWebMenu(Integer startIndex, Integer pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        //1、调用dao层获取分页后的数据
        List<Map<String, Object>> menuList = webMenuMapper.selectWebMenu(startIndex, pageSize);
        //2、调用dao层获取web_menu表中的总记录数
        int i = webMenuMapper.countWebMenu();
        resultMap.put("rows", menuList);
        resultMap.put("total", i);
        return resultMap;
    }

    @Override
    public boolean saveWebMenu(Map<String, Object> paramMap) {
        return webMenuMapper.insertWebMenu(paramMap) >= 1;
    }

    @Override
    public boolean updateWebMenu(Map<String, Object> paramMap) {
        return webMenuMapper.updateWebMenu(paramMap) >= 1;
    }

    @Override
    public boolean batchDelWebMenu(Map<String, Object> paramMap) {
        return webMenuMapper.batchDelWebMenu(paramMap) >= 1;
    }
}