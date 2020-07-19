package com.gmall.user;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ClassName GmallUserService
 * PackageName com.gmall.user
 *
 * @Description
 * @auther wang
 * @create 2020-06-30
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.gmall.user.mapper"})
public class GmallUserService {

    public static void main(String[] args) {
        SpringApplication.run(GmallUserService.class,args);
    }

}
