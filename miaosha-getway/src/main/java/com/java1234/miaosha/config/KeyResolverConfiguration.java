package com.java1234.miaosha.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流规则配置类
 *
 * @author java1234_小锋
 * @site www.java1234.com
 * @company Java知识分享网
 * @create 2021-01-23 11:16
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
