package com.java.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：15:59
 */
public interface LoginMapper {

    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @return
     */
    @Select("SELECT COUNT(*) FROM admin_users WHERE username=#{username} AND pwd=#{pwd}")
    int login(String username, String pwd);

    /**
     * 根据用户名获取对应的权限
     */
    List<Map<String, Object>> getAuthorityByUsername(Map<String, Object> paramMap);

    /**
     * 根据用户名获取角色状态码
     *
     * @param username
     * @return
     */
    @Select("SELECT isRoot FROM admin_users WHERE username=#{username}")
    String getRootByUsername(String username);
}
