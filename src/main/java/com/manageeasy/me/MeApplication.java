package com.manageeasy.me;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MapperScan("com.manageeasy.me.Daos")
@SpringBootApplication

public class MeApplication{

    public static void main(String[] args) {
        SpringApplication.run(MeApplication.class, args);
    }
}
