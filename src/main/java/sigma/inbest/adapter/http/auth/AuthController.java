package sigma.inbest.adapter.http.auth;

import org.springframework.web.bind.annotation.*;
import sigma.inbest.adapter.http.auth.request.LogInRequest;
import sigma.inbest.adapter.http.auth.request.SetSimplePasswordRequest;
import sigma.inbest.adapter.http.auth.request.SimpleLogInRequest;
import sigma.inbest.application.auth.interacter.AuthUseCase;
import sigma.inbest.adapter.http.auth.request.SignUpRequest;
import sigma.inbest.application.auth.interacter.response.TokenResponse;
import sigma.inbest.application.global.response.HttpStateResponse;
import sigma.inbest.domain.user.User;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUseCase authUseCase;

    @PostMapping("/signup")
    public HttpStateResponse<User> signup(@RequestBody SignUpRequest request){
        return authUseCase.signup(request.email(), request.password(), request.nickname());
    }

    @PatchMapping("/setSimplePassword")
    public HttpStateResponse<String> setSimplePassword(@RequestBody SetSimplePasswordRequest setSimplePasswordRequest){
        return authUseCase.setSimplePassword(setSimplePasswordRequest.email(), setSimplePasswordRequest.simplePassword());
    }

    @GetMapping("/login")
    public HttpStateResponse<TokenResponse> login(@RequestBody LogInRequest logInRequest){
        return authUseCase.login(logInRequest.email(), logInRequest.password());
    }

    @GetMapping("/newAccessToken")
    public HttpStateResponse<TokenResponse> newAccessToken(@RequestBody String refreshToken){
        return authUseCase.newAccessToken(refreshToken);
    }

    @GetMapping("/simpleLogin")
    public HttpStateResponse<TokenResponse> simpleLogin(@RequestBody SimpleLogInRequest simpleLogInRequest){
        return authUseCase.simpleLogin(simpleLogInRequest.tokenResponse(), simpleLogInRequest.simplePassword());
    }

}
