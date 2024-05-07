package sigma.inbest.application.auth.interacter.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import sigma.inbest.application.global.response.InbestException;

@Component
@RequiredArgsConstructor
public class Validator {
    public void isValidEmail(String email) {
        if(!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
            throw new InbestException(HttpStatus.BAD_REQUEST, "이메일 형식이 잘못되었습니다.");
        }
    }

    public void isValidPassword(String password) {
        if(password.length() < 4 || password.length() > 15){
            throw new InbestException(HttpStatus.BAD_REQUEST, "비밀번호는 4자 이상, 15자 이하의 문자를 사용해야 합니다.");
        }

        if(!password.matches("^[0-9a-zA-Z~!@#$%^&*+=]*$")){
            throw new InbestException(HttpStatus.BAD_REQUEST, "비밀번호는 영문자, 숫자, 특수문자 외에 요소가 추가될 수 없습니다.");
        }

        if (!password.matches("^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*+=()_-])(?=.*[0-9]).+$")) {
            throw new InbestException(HttpStatus.BAD_REQUEST,"비밀번호는 영문자, 숫자, 특수문자를 조합하여 작성해야 합니다.");
        }
    }
}
