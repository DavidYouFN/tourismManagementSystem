package com.felix.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.felix.project")
@MapperScan(basePackages = {"com.felix.project.user.mapper","com.felix.project.shopCar.mapper","com.felix.project.order.mapper","com.felix.project.commodity.mapper"})
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
