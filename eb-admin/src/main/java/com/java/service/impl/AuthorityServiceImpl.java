package com.java.service.impl;

import com.java.mapper.AuthorityMapper;
import com.java.pojo.admin.OneMenu;
import com.java.service.AuthorityService;
import com.java.utils.MD5Too;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<Map<String, Object>> findSystemUser() {
        return authorityMapper.getSystemUser();
    }

    @Override
    public List<Map<String, Object>> findAuthority(Long parentId) {
        return authorityMapper.getAuthority(parentId);
    }

    @Transactional(readOnly = false)
    @Override
    public boolean saveSystemUser(String username, String pwd, String menuIds) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("pwd", MD5Too.MD5(pwd));
        int flag = authorityMapper.addSystemUser(paramMap);
        if (flag >= 1) {
            Long userId = (Long) paramMap.get("userId");
            String[] menuIdAttr = menuIds.split("\\,");
            for (String menuId : menuIdAttr) {
                int flag2 = authorityMapper.addUserAuthority(userId, Long.parseLong(menuId));
                if (flag2 <= 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<OneMenu> findAuthorityByRelation() {
        return authorityMapper.getAuthorityByRelation();
    }
}
