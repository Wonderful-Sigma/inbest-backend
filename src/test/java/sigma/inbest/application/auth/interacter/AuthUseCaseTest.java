package sigma.inbest.application.auth.interacter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import sigma.inbest.application.global.response.HttpStateResponse;
import sigma.inbest.domain.user.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class AuthUseCaseTest {

    @Autowired
    AuthUseCase authUseCase;


    @Test
    void signup() {
        //given
        String email = "wkdqjawns@dgsw.hs.kr";
        String password = "jang0321+";
        String nickName = "문어";
        //when
        HttpStateResponse<User> response = authUseCase.signup(email,password,nickName);
        //then
        assertEquals(response.getState(), HttpStatus.OK);
        User user = response.getBody();
        assertEquals(user.getEmail().email(), email);
        assertEquals(user.getPasswords().password(), password);
        assertEquals(user.getUserInfo().nickname(), nickName);
    }

    @Test
    void setSimplePassword() {
        //given

        //when

        //then
    }

    @Test
    void login() {
        //given

        //when

        //then

    }

    @Test
    void newAccessToken() {
        //given

        //when

        //then

    }

    @Test
    void simpleLogin() {
        //given

        //when

        //then

    }
}