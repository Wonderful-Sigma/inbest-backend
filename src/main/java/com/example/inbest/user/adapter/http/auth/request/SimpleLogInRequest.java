package com.example.inbest.user.adapter.http.auth.request;

import com.example.inbest.user.application.auth.interacter.response.TokenResponse;

public record SimpleLogInRequest(TokenResponse tokenResponse, String simplePassword) {
}
