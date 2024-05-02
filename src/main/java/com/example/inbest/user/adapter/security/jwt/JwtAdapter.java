package com.example.inbest.user.adapter.security.jwt;

import com.example.inbest.user.application.auth.interacter.response.TokenResponse;
import com.example.inbest.user.application.auth.port.out.TokenPort;
import com.example.inbest.user.application.global.response.HttpStateResponse;
import com.example.inbest.user.application.global.response.InbestException;
import com.example.inbest.user.application.user.UserDBPort;
import com.example.inbest.user.domain.user.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAdapter implements TokenPort {

    private final JwtProperties jwtProperties;
    private final UserDBPort userDBPort;


    @Override
    public TokenResponse issueToken(final String email, final String password) {
        return new TokenResponse(
                generateToken(email,password,JwtType.ACCESS),
                generateToken(email,password,JwtType.REFRESH)
        );
    }

    @Override
    public User parseToken(String Token, JwtType jwtType) {
        final String key;
        switch (jwtType){
            case ACCESS -> key = jwtProperties.getAccessKey();
            case REFRESH -> key = jwtProperties.getRefreshKey();
            default -> throw new IllegalStateException("Unexpected value: " + jwtType);
        }

        final Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(Token);

        if(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType)){
            throw new InbestException(HttpStatus.BAD_REQUEST, "토큰의 타입이 틀렸습니다.");
        }

        String email = claims.getBody().get("email", String.class);
        Optional<User> user = userDBPort.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }else{
            throw new InbestException(HttpStatus.BAD_REQUEST, "token 속 계정은 존재하지 않는 계정입니다.");
        }
    }

    @Override
    public HttpStateResponse<String> newAccessToken(String refreshToken) {
        return null;
    }

    private String generateToken(final String email,final String password ,final JwtType jwtType) {
        final String key;
        final long expire;

        Claims claims = Jwts.claims();
        claims.put("email",email);
        claims.put("password",password);

        switch (jwtType) {
            case ACCESS -> {
                key = jwtProperties.getAccessKey();
                expire = Long.parseLong(jwtProperties.getAccessExpire());
            }
            case REFRESH -> {
                key = jwtProperties.getRefreshKey();
                expire = Long.parseLong(jwtProperties.getRefreshExpire());
            }
            default -> throw new IllegalStateException("Unexpected value: " + jwtType);
        }

        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, jwtType)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
