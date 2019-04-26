package com.java.service;

import java.util.List;
import java.util.Map;

public interface WebMenuService {

    Map<String, Object> findWebMenu(Integer startIndex, Integer pageSize);

    boolean saveWebMenu(Map<String, Object> paramMap);

    boolean updateWebMenu(Map<String, Object> paramMap);

    boolean batchDelWebMenu(Map<String, Object> paramMap);
}
