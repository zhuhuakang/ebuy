package com.java.controller;

import com.java.pojo.admin.OneMenu;
import com.java.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    /**
     * 获取系统用户
     *
     * @return
     */
    @RequestMapping("/getSystemUser.do")
    @ResponseBody
    public List<Map<String, Object>> getSystemUser() {
        return authorityService.findSystemUser();
    }

    @RequestMapping("/getAuthority.do")
    @ResponseBody
    public List<Map<String, Object>> getAuthority(@RequestParam(name = "id", defaultValue = "0") Long id) {
        return authorityService.findAuthority(id);
    }

    /**
     * 添加系统用户并且授权
     *
     * @param username
     * @param pwd
     * @param menuIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addSystemUser.do")
    @ResponseBody
    public boolean addSystemUser(String username, String pwd, String menuIds) throws Exception {
        return authorityService.saveSystemUser(username, pwd, menuIds);
    }

    @RequestMapping("/findAuthorityByRelation.do")
    @ResponseBody
    public List<OneMenu> findAuthorityByRelation() {
        return authorityService.findAuthorityByRelation();
    }
}
