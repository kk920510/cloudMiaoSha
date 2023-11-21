package com.java1234.miaosha.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitmq配置类
 * @author zk
 * @create 2023-11-17 20:58
 */
@Configuration
public class MQConfig {

    public static final String MIAOSHA_QUEUE="miaosha_queue";

    @Bean
    public Queue queue(){
        return new Queue(MIAOSHA_QUEUE);
    }
}
