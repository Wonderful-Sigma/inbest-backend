package sigma.inbest.application.auth.interacter;

import sigma.inbest.adapter.security.jwt.JwtType;
import sigma.inbest.application.auth.interacter.response.TokenResponse;
import sigma.inbest.application.auth.interacter.validator.Validator;
import sigma.inbest.application.user.UserDBPort;
import sigma.inbest.application.auth.port.out.TokenPort;
import sigma.inbest.application.global.response.HttpStateResponse;
import sigma.inbest.application.global.response.InbestException;
import sigma.inbest.domain.user.User;
import sigma.inbest.domain.user.value.UserId;
import sigma.inbest.domain.user.value.UserInfo;
import sigma.inbest.domain.user.value.UserPassword;
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
    private final Validator validator;

    public HttpStateResponse<User> signup(String email, String password, String nickname){
        Optional<User> testDum = userDBPort.findByEmail(email);
        if(testDum.isPresent()) throw new InbestException(HttpStatus.BAD_REQUEST,"이미 가입한 이메일입니다.");
        validator.isValidEmail(email);
        validator.isValidPassword(password);
        final User user = userDBPort.save(new User(new UserId(email),new UserPassword(password, "Undefined"), new UserInfo(nickname)));
        // 계좌 생성 부분 추가
        return new HttpStateResponse<>(HttpStatus.OK,"회원가입을 성공했습니다!", user);
    }

    public HttpStateResponse<String> setSimplePassword(String email, String simplePassword){
        User user = userDBPort.findByEmail(email).orElseThrow();

        user.setPasswords(new UserPassword(user.getPasswords().password(), simplePassword));

        return new HttpStateResponse<>(HttpStatus.OK,"간편비밀번호 설정 성공", "팬서비스");
    }

    public HttpStateResponse<TokenResponse> login(String email, String password){
        TokenResponse tokenResponse = null;
        // 없는 경우 처리;
        String userPassword = userDBPort.findByEmail_for_passwd(email);
        if(Objects.equals(userPassword, password)) tokenResponse = tokenPort.issueToken(email,password);
        else new HttpStateResponse<>(HttpStatus.BAD_REQUEST,"비밀번호를 틀렸습니다.",null);

        return new HttpStateResponse<>(HttpStatus.OK, "로그인을 성공했습니다!",tokenResponse);
    }

    public HttpStateResponse<TokenResponse> newAccessToken(String refreshToken){
        TokenResponse tokenResponse = new TokenResponse(tokenPort.newAccessToken(refreshToken),"");

        return new HttpStateResponse<>(HttpStatus.OK,"새 accessToken이 발급되었습니다.", tokenResponse);
    }

    public HttpStateResponse<TokenResponse> simpleLogin(TokenResponse tokenResponse, String simplePassword){
        User user = tokenPort.parseToken(tokenResponse.refreshToken(), JwtType.REFRESH);
        if(Objects.equals(user.getPasswords().simplePassword(), simplePassword)) return new HttpStateResponse<>(HttpStatus.OK,"간편 로그인을 성공했습니다.",tokenPort.issueToken(user.getEmail().email(),user.getPasswords().password()));
        return new HttpStateResponse<>(HttpStatus.BAD_REQUEST,"간편 비밀번호를 틀렸습니다.",null);
    }
}
