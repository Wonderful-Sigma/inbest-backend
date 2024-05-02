package com.example.inbest.user.application.auth.port.out;

import com.example.inbest.user.adapter.security.jwt.JwtType;
import com.example.inbest.user.application.auth.interacter.response.TokenResponse;
import com.example.inbest.user.application.global.response.HttpStateResponse;
import com.example.inbest.user.domain.user.User;
import org.springframework.http.HttpStatus;

public interface TokenPort {
    TokenResponse issueToken(String email,String password);

    User parseToken(String Token, JwtType jwtType);

    HttpStateResponse<String> newAccessToken(String refreshToken);
}
