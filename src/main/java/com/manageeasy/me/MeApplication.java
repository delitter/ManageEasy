package com.manageeasy.me;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.MultipartConfigElement;

@RestController
@MapperScan("com.manageeasy.me.Daos")
@SpringBootApplication
@Configuration
public class MeApplication{

    public static void main(String[] args) {
        SpringApplication.run(MeApplication.class, args);
    }

    /**
     * 配置上传文件大小的配置，要新建DataSize实体，直接传字符串的方法已弃用
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        DataSize dataSize = DataSize.ofBytes(10485760);
        factory.setMaxFileSize(dataSize);
        /// 总上传数据大小
        factory.setMaxRequestSize(dataSize);
        return factory.createMultipartConfig();
    }
}
