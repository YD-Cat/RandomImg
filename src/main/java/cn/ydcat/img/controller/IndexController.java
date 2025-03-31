package cn.ydcat.img.controller;

import cn.ydcat.img.config.ImgConfig;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Random;

@RestController
public class IndexController {

    @Autowired
    private ImgConfig imgConfig;

    @SneakyThrows
    @GetMapping("/")
    public void index(HttpServletResponse response) {
        // 设置页面不缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        OutputStream outputStream = response.getOutputStream();
        String imgPath = imgConfig.getImgPath();
        File imgFile = new File(imgPath);
        File[] files = imgFile.listFiles();
        Random r = new Random();
        int i = r.nextInt(files.length);
        File file = files[i];
        FileInputStream fileInputStream = new FileInputStream(file);
        IOUtils.copy(fileInputStream, outputStream);
    }
}
