package com.example.inbest.user.adapter.http.auth;

import com.example.inbest.user.application.auth.interacter.AuthUseCase;
import com.example.inbest.user.adapter.http.auth.request.SignUpRequest;
import com.example.inbest.user.application.global.response.HttpStateResponse;
import com.example.inbest.user.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUseCase authUseCase;

    @PostMapping("/signup")
    public HttpStateResponse<User> signup(@RequestBody SignUpRequest request){
        return authUseCase.signup(request.email(), request.password(), request.nickname());
    }
}
