package com.java1234.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.java1234.miaosha.feign")
@SpringBootApplication
@MapperScan("com.java1234.miaosha.mapper")
@EnableDiscoveryClient
public class  MiaoshaMiaoshaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiaoshaMiaoshaApplication.class, args);
    }
}

