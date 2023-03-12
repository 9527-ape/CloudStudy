package com.jiajia.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//让RestTemplate以默认方式的负载均衡模式去请求，调用不同的生产者
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
