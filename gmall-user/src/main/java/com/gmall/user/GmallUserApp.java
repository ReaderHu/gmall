package com.gmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ClassName GmallUserApp
 * PackageName com.gmall.user
 *
 * @Description
 * @auther wang
 * @create 2020-06-30
 */
@SpringBootApplication
@MapperScan(basePackages = "com.gmall.user.mapper")
public class GmallUserApp {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserApp.class, args);
    }


}
