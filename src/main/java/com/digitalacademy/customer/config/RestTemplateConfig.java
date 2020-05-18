package com.digitalacademy.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${restTemplate.connectionTimeOut}")
    private int connection;

    @Value("${restTemplate.readTimeOut}")
    private int readTimeOut;

    @Bean
    public RestTemplate getResTemplate(){
        return new RestTemplate();
    }
}
