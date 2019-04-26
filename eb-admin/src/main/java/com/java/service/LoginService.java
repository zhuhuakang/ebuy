package com.java.service;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:02
 */
public interface LoginService {
    //登录
    boolean login(String username, String pwd);

    //查询权限
    List<Map<String, Object>> findAuthorityByUsername(Map<String, Object> paramMap);
}
