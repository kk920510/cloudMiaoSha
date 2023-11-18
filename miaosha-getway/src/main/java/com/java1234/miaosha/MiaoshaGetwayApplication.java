package com.java1234.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MiaoshaGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoshaGetwayApplication.class, args);
    }

}

