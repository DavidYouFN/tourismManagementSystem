package com.felix.project.user.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AliyunOssProperties
 * @Description TODO
 * @Author fangyong
 * @Date 2019/5/14 10:15
 **/
@Configuration
@ConfigurationProperties("aliyun.oss")
@Data
public class AliyunOssProperties {
    private String accessId;
    private String accessKey;
    private String bucket;
    private String endpoint;
    private String dir;
    private Integer maxSize = Integer.valueOf(1);
    private Integer expire = Integer.valueOf(30);
    private boolean secure = false;
    private String roleSessionName;

    public AliyunOssProperties() {
    }


}
