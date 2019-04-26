package com.java.controller;

import com.java.service.IndexService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：09:30
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private Configuration fkConfig;

    /**
     * 获取横向导航栏
     *
     * @return
     */
    @RequestMapping("/getHxWebMenu.do")
    public @ResponseBody
    List<Map<String, Object>> getHxWebMenu() {
        return indexService.findWebMenus("1");
    }

    //通过注解式来开发redis
    @RequestMapping("/getZXByRedisZJ.do")
    public @ResponseBody
    List<Map<String, Object>> getZXByRedisZJ() {
        return indexService.findZXMenu();
    }

    //通过注解式来开发redis
    @RequestMapping("/clear.do")
    public void clear() {
        indexService.clearZxMenu();
    }

    //---------------------------------使用freemaker静态化页面---------------------------------

    /**
     * 进入商品详情:传递productId参数，并且通过freemaker获取数据
     *
     * @return
     */
    @RequestMapping("/toProductDetailFTL/{productId}")
    public ModelAndView toProductDetailFTL(@PathVariable(name = "productId") Long productId) {
        //模拟假数据-实际从后台数据库获取
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", "联想(Lenovo)拯救者Y7000 第八代");
        dataMap.put("subTitle", "Y7000爆款机型，512G_PCIE固态硬盘海量存储更快捷");
        dataMap.put("price", 5699.00F);
        dataMap.put("type", "经典版");
        dataMap.put("color", "银白色");
        dataMap.put("num", 100);
        //图片
        List<String> imgUrlList = new ArrayList<>();
        imgUrlList.add("http://192.168.25.133/group1/M00/00/04/wKgZhVxozjWACYeZAACmK0sntbI152.jpg");
        imgUrlList.add("http://192.168.25.133/group1/M00/00/04/wKgZhVxp_zmAD0HzAADtVrCvlSc088.jpg");
        imgUrlList.add("http://192.168.25.133/group1/M00/00/04/wKgZhVxqI2GAO_0nAABn0cwGIro754.jpg");
        dataMap.put("imgUrlList", imgUrlList);
        //将数据保存到request域中并且跳转到Product--->Product.html
        ModelAndView mv = new ModelAndView("Product");
        mv.addAllObjects(dataMap);
        //mv.addObject("dataMap",dataMap);
        return mv;
    }

    /**
     * 通过freemaker动态生成html文件
     *
     * @throws Exception
     */
    @RequestMapping("/doStaticProductDetail.do")
    public @ResponseBody
    String doStaticProductDetail() throws Exception {
        //从后台数据库获取
        List<Map<String, Object>> productDetailList = indexService.findAllProductDetail();
        //freemaker取出数据后，生成静态的HTML页面(productId.html)
        //1、获取指定的freemaker模板对象
        File file = null;
        for (Map<String, Object> tempMap : productDetailList) {
            Template template = fkConfig.getTemplate("Product.ftl");
            file = new File("E:\\fremaker\\details\\" + tempMap.get("id") + ".html");
            FileWriter out = new FileWriter(file);
            template.process(tempMap, out);
            out.close();
        }
        return file.getPath();
    }
}
