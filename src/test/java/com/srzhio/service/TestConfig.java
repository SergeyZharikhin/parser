package com.srzhio.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public LineProcessor lineProcessor(){
        return new LineProcessor();
    }

    @Bean
    public TokenProcessor tokenProcessor(){
        return new TokenProcessor();
    }

    @Bean
    public HtmlGenerator htmlGenerator(){
        return new HtmlGenerator();
    }
}
