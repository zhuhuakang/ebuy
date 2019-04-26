package com.java.controller;

import com.java.service.WebMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebMenuController {
    @Autowired
    private WebMenuService webMenuService;

    /**
     * 分页查询横向菜单
     *
     * @return
     */
    @RequestMapping("/getHxMenu.do")
    @ResponseBody
    public Map<String, Object> getHxMenu(Integer page, Integer rows) {
        int startIndex = (page - 1) * rows;
        return webMenuService.findWebMenu(startIndex, rows);
    }

    /**
     * 添加横向导航栏
     *
     * @param title
     * @param url
     * @return
     */
    @RequestMapping("/addWebMenu.do")
    public @ResponseBody
    boolean addWebMenu(String title, String url) {
        //1、校验title、url是否满足格式要求
        if (title == null || url == null) {
            return false;
        }
        boolean flag1 = title.matches(".{1,10}");
        boolean flag2 = url.matches("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
        if (!flag1 || !flag2) {
            return false;
        }
        //2、满足格式要求才调用业务层
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", title);
        paramMap.put("url", url);
        paramMap.put("menuType", "1");
        return webMenuService.saveWebMenu(paramMap);
    }

    /**
     * 修改前台横向导航栏
     *
     * @param title
     * @param url
     * @return
     */
    @RequestMapping("/updateWebMenu.do")
    public @ResponseBody
    boolean updateWebMenu(String title, String url, Long id) {
        //1、校验title、url是否满足格式要求
        if (title == null || url == null) {
            return false;
        }
        boolean flag1 = title.matches(".{1,10}");
        boolean flag2 = url.matches("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
        if (!flag1 || !flag2) {
            return false;
        }
        //2、满足格式要求才调用业务层
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", title);
        paramMap.put("url", url);
        paramMap.put("id", id);
        return webMenuService.updateWebMenu(paramMap);
    }

    /**
     * 批量删除
     *
     * @param idStr
     * @return
     */
    @RequestMapping("/deleteWebMenu.do")
    public @ResponseBody
    boolean deleteWebMenu(String idStr) {
        //idStr="1   ,   2    ,   3   ,"
        idStr = idStr.substring(0, idStr.length() - 1);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idStr", idStr);
        return webMenuService.batchDelWebMenu(paramMap);
    }

}
