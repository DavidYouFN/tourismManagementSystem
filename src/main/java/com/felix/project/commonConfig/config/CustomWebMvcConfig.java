package com.felix.project.commonConfig.config;

import com.felix.project.commonConfig.util.StringToDateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @Project:tgw
 * @Description:custom mvc config
 * @Author:TjSanshao
 * @Create:2018-12-10 16:15
 *
 **/
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
    }

}
