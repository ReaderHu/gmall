package com.gmall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName GmallUserApp
 * PackageName com.gmall.user
 *
 * @Description
 * @auther wang
 * @create 2020-06-28
 */
@SpringBootApplication
@MapperScan
public class GmallUserApp {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserApp.class, args);
    }


}
