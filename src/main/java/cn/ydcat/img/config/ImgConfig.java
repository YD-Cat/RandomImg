package cn.ydcat.img.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author kate.liu
 * @date 2025-03-31
 */
@Data
@Configuration
public class ImgConfig {

    @Value("${img.linuxPath}")
    private String linuxPath;

    @Value("${img.winPath}")
    private String winPath;

    public String getImgPath(){
        String osName = System.getProperty("os.name");
        if(osName.toLowerCase().contains("windows")){
            return winPath;
        } else {
            return linuxPath;
        }
    }
}
