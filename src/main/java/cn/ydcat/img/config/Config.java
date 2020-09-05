package cn.ydcat.img.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置本地图片映射到网络路径
 */
@Configuration // 1.添加配置文件注解
public class Config implements WebMvcConfigurer { // 2.实现WebMvcConfigurer
    @Value("${img.path}")
    private String locationPath; // 3.本地路径
    private static final String netPath = "/img/**"; // 映射路径

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(netPath).addResourceLocations("file:"+locationPath);
    }
}
