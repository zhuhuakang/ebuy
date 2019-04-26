package com.java.controller;

import com.java.service.LoginService;
import com.java.utils.MD5Too;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：15:43
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param username
     * @param pwd
     * @return
     */
    @RequestMapping("/login.do")
    public String login(String username, String pwd, HttpSession session) throws Exception {//NULL
        System.out.println("username=" + username + ",pwd=" + pwd);
        //没有做任何的校验
        if (username == null || pwd == null) {
            return "login";
        }
        boolean flag1 = username.matches(".{3,12}");
        boolean flag2 = pwd.matches(".{6,12}");
        if (!flag1 || !flag2) {
            return "login";
        }
        //没有对密码进行加密
        pwd = MD5Too.MD5(pwd);
        boolean flag = loginService.login(username, pwd);
        if (flag) {
            session.setAttribute("username", username);
        }
        return flag ? "index" : "login";
    }

    @RequestMapping("/getAuthorityByUsername.do")
    @ResponseBody
    public List<Map<String, Object>> getAuthorityByUsername(@RequestParam(name = "id", defaultValue = "0") Long id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("id", id);
        return loginService.findAuthorityByUsername(paramMap);
    }
}
