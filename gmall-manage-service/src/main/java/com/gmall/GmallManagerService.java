package com.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ClassName GmallManagerService
 * PackageName com.gmall.manager
 *
 * @Description
 * @auther wang
 * @create 2020-07-05
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.gmall.manager.mapper"})
public class GmallManagerService {

    public static void main(String[] args) {
        SpringApplication.run(GmallManagerService.class,args);
    }

}
