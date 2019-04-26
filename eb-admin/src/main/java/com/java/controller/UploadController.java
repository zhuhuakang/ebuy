package com.java.controller;

import com.yuqing.common.FastDFSClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {

    //连接fastdfs的文件上传程序
    @RequestMapping("/fastDFSUpload.do")
    public @ResponseBody
    Map<String, Object> fastDFSUpload(MultipartFile uploadFile) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            FastDFSClient client = new FastDFSClient("classpath:resources/fdfs_client.conf");
            //获取上传文件的源文件名
            String oldName = uploadFile.getOriginalFilename();
            //获取后缀名
            String extName = oldName.substring(oldName.lastIndexOf(".") + 1);
            //开始将文件保存到fastdfs服务器中去，并且返回相对地址  12\\23\\56\\17\\1.jpg
            String basepath = client.uploadFile(uploadFile.getBytes(), extName);
            basepath = "http://192.168.25.133/" + basepath;
            resultMap.put("status", "0");//status代表的是状态码，0代表成功
            resultMap.put("basepath", basepath);
            System.out.println("basePath=" + basepath);
            return resultMap;
        } catch (Exception e) {
            resultMap.put("status", "1");//1代表文件上传失败
            return resultMap;
        }
    }

    /**
     * 本地文件上传
     *
     * @param ff
     */
    @RequestMapping("/uploadFile.do")
    public void uploadFile(MultipartFile ff) throws IOException {
        //获取系统时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd\\HH\\mm\\ss");
        String basePath = sdf.format(date);//  20190217\\12\12\12
        File file = new File("E:\\uploads\\" + basePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取上传文件的源文件名 abc.def.aaa.log    c:\\users\\desktop\\aaa.log
        String orginalName = ff.getOriginalFilename();
        //获取上传文件的后缀名
        String exetName = orginalName.substring(orginalName.lastIndexOf("."));
        //动态生成文件名
        String fileName = UUID.randomUUID().toString();
        //动态生成文件夹名
        String dynamicDir = basePath + "\\" + fileName + exetName;
        System.out.println("dynamicDir=" + dynamicDir);
        ff.transferTo(new File(dynamicDir));
    }
}