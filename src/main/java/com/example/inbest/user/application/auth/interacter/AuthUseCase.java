package com.example.inbest.user.application.auth.interacter;

import com.example.inbest.user.adapter.security.jwt.JwtType;
import com.example.inbest.user.adapter.http.auth.request.LogInRequest;
import com.example.inbest.user.adapter.http.auth.request.SimpleLogInRequest;
import com.example.inbest.user.application.auth.interacter.response.TokenResponse;
import com.example.inbest.user.application.auth.port.out.TokenPort;
import com.example.inbest.user.application.global.response.HttpStateResponse;
import com.example.inbest.user.application.global.response.InbestException;
import com.example.inbest.user.application.user.UserDBPort;
import com.example.inbest.user.domain.user.User;
import com.example.inbest.user.domain.user.value.UserId;
import com.example.inbest.user.domain.user.value.UserInfo;
import com.example.inbest.user.domain.user.value.UserPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthUseCase {
    private final UserDBPort userDBPort;
    private final TokenPort tokenPort;

    public HttpStateResponse<User> signup(String email, String password, String nickname){
        Optional<User> testDum = userDBPort.findByEmail(email);
        if(testDum.isPresent()) throw new InbestException(HttpStatus.BAD_REQUEST,"이미 가입한 이메일입니다.");
        final User user = userDBPort.save(new User(new UserId(email),new UserPassword(password, null), new UserInfo(nickname)));
        // 계좌 생성 부분 추가
        return new HttpStateResponse<>(HttpStatus.OK,"회원가입을 성공했습니다!", user);
    }

    public HttpStateResponse<String> setSimplePassword(String email, String simplePassword){
        User user = userDBPort.findByEmail(email).orElseThrow();

        user.setPasswords(new UserPassword(user.getPasswords().password(), simplePassword));

        return new HttpStateResponse<>(HttpStatus.OK,"간편비밀번호 설정 성공", "Dummy");
    }

    public HttpStateResponse<TokenResponse> login(LogInRequest logInIngredient){
        TokenResponse tokenResponse = null;
        // 없는 경우 처리;
        String password = userDBPort.findByEmail_for_passwd(logInIngredient.email());
        if(Objects.equals(password, logInIngredient.password())) tokenResponse = tokenPort.issueToken(logInIngredient.email(),logInIngredient.password());
        else new HttpStateResponse<>(HttpStatus.BAD_REQUEST,"비밀번호를 틀렸습니다.",null);

        return new HttpStateResponse<>(HttpStatus.OK, "로그인을 성공했습니다!",tokenResponse);
    }

    public HttpStateResponse<String> newAccessToken(String refreshToken){
        return tokenPort.newAccessToken(refreshToken);
    }

    public HttpStateResponse<TokenResponse> simpleLogin(SimpleLogInRequest simpleLogInRequest){
        User user = tokenPort.parseToken(simpleLogInRequest.tokenResponse().refreshToken(), JwtType.REFRESH);
        if(Objects.equals(user.getPasswords().simplePassword(), simpleLogInRequest.simplePassword())) return new HttpStateResponse<>(HttpStatus.OK,"간편 로그인을 성공했습니다.",tokenPort.issueToken(user.getEmail().email(),user.getPasswords().password()));
        return new HttpStateResponse<>(HttpStatus.BAD_REQUEST,"간편 비밀번호를 틀렸습니다.",null);
    }
}
