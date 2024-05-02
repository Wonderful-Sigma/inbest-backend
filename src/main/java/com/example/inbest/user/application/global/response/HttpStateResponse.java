package com.example.inbest.user.application.global.response;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HttpStateResponse<T>{
    private final HttpStatus state;
    private final String message;
    @org.springframework.lang.Nullable
    private final T body;

    public HttpStateResponse(HttpStatus status, @Nullable String message, @Nullable T body) {
        this.state = status;
        this.message = message;
        this.body = body;
    }
}
