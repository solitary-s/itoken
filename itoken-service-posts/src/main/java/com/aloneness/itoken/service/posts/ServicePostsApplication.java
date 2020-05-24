package com.aloneness.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.aloneness.itoken.common.context", "com.aloneness.itoken.service.posts"})
@EnableEurekaClient
@EnableSwagger2
@MapperScan(basePackages = {"com.aloneness.itoken.service.posts.mapper", "com.aloneness.itoken.common.mapper"})
public class ServicePostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePostsApplication.class, args);
    }
}
