package com.gmall.user;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName GmallUserWeb
 * PackageName com.gmall.user
 *
 * @Description
 * @auther wang
 * @create 2020-06-30
 */
@SpringBootApplication
public class GmallUserWeb {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserWeb.class,args);
    }

}
