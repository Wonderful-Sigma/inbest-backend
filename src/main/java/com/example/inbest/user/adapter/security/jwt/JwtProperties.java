package com.example.inbest.user.adapter.security.jwt;


import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String accessKey;
    private String refreshKey;
    private String accessExpire;
    private String refreshExpire;
}
