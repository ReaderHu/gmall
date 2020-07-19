package com.gmall.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class GmallItemWeb {

    public static void main(String[] args) {
        SpringApplication.run(GmallItemWeb.class, args);
    }

}
