package com.java1234.miaosha.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流规则配置类
 *
 * @author zk
 * @create 2023-11-17
 */
@Configuration
public class KeyResolverConfiguration {

    @Bean
    public KeyResolver pathKeyResolver() {

        /*return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getURI().getPath());
            }
        };*/

        // 根据url 根据path 来取令牌
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }
}
