package com.manageeasy.me;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.manageeasy.me.Daos")
@SpringBootApplication
public class MeApplication{

    public static void main(String[] args) {
        SpringApplication.run(MeApplication.class, args);
    }
}
