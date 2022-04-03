package cn.ydcat.img.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Controller
public class IndexController {
    // 本地图片路径
    @Value("${img.path}")
    private String path;
    // 第三方随机图片接口
    private String url = "https://api.ixiaowai.cn/api/api.php";

    @GetMapping("/")
    public String img(HttpServletResponse response){
        // 设置页面不缓存
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // 从图片库中随机获取一张图片
        File file = new File(path+"/images/");
        File[] files = file.listFiles();
        // 通过转发访问本地图片
        try{
            if (files.length > 0) {
            Random r = new Random();
            int i = r.nextInt(files.length);
                String name = files[i].getName();
                url = "static/images/" + name;
                return "forward:" + url;
            } else {
                // 没有配置本地图片，重定向到第三方
                return "redirect:" + url;
            }
        }catch (Exception e){
            // 异常，重定向到第三方
            return "redirect:" + url;
        }
    }

    @GetMapping("/admin")
    public String adminIndex(){
        return "static/admin/index.html";
    }
}
