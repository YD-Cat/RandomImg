package cn.ydcat.img.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.ydcat.img.config.ImgConfig;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.InputStream;
import java.util.Random;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private ImgConfig imgConfig;

    @SneakyThrows
    @GetMapping("/")
    public String index(HttpServletResponse response) {
        // 设置页面不缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        String imgPath = imgConfig.getImgPath();
        File fileDire = new File(imgPath);
        String url = "";
        try{
            // 从本地随机获取一张图片
            File[] files = fileDire.listFiles();
            if (files != null && files.length > 0) {
                Random r = new Random();
                int i = r.nextInt(files.length);
                String name = files[i].getName();
                // 通过转发访问本地图片
                url = "forward:static/" + name;
                return url;
            } else {
                throw new RuntimeException("没有图片文件");
            }
        }catch (Exception e){
            log.error("异常：{}，文件路径：{}", e.getMessage(), imgPath, e);
//            try {
//                // 异常，重定向到第三方
//                url = imgConfig.getApiUrl();
//                HttpRequest get = HttpUtil.createGet(url, true);
//                HttpResponse execute = get.execute();
//                InputStream inputStream = execute.bodyStream();
//
//                ServletOutputStream outputStream = response.getOutputStream();
//                IOUtils.copy(inputStream, outputStream);
//            } catch (Exception e1) {
//                e.printStackTrace();
//            }
            return null;
        }
    }
}
