package com.sirio.api.configs;

import com.sirio.api.configs.jwt.JwtAuthenticationFilter;
import com.sirio.api.configs.jwt.JwtTokenProvider;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }
    
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
}
