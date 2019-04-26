package com.java.tasks;

import com.java.service.IndexService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

@Component
public class Task1 {

    @Autowired
    private IndexService indexService;

    @Autowired
    private Configuration fkConfig;

    //@Scheduled：称之为任务调度，其下有很多属性
    //fixedRate属性：按照固定的时间去执行方法
    //cron属性：
    //@Scheduled(fixedRate = 1000)
    @Scheduled(cron = "1 0/5 * * * *")
    public void test1() throws Exception {
        //从后台数据库获取
        List<Map<String, Object>> productDetailList = indexService.findUpdatedProductDetail();
        //freemaker取出数据后，生成静态的HTML页面(productId.html)
        //1、获取指定的freemaker模板对象
        File file = null;
        for (Map<String, Object> tempMap : productDetailList) {
            Template template = fkConfig.getTemplate("Product.ftl");
            file = new File("D:\\fremaker\\details\\" + tempMap.get("id") + ".html");
            FileWriter out = new FileWriter(file);
            template.process(tempMap, out);
            out.close();
        }
        System.out.println("重新生成的HTML文件地址=");
    }

}
