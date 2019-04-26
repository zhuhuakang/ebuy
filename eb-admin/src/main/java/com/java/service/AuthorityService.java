package com.java.service;

import com.java.pojo.admin.OneMenu;

import java.util.List;
import java.util.Map;

public interface AuthorityService {
    List<Map<String, Object>> findSystemUser();

    List<Map<String, Object>> findAuthority(Long parentId);

    boolean saveSystemUser(String username, String pwd, String menuIds) throws Exception;

    List<OneMenu> findAuthorityByRelation();
}
