package sigma.inbest.application.auth.port.out;

import sigma.inbest.adapter.security.jwt.JwtType;
import sigma.inbest.application.auth.interacter.response.TokenResponse;
import sigma.inbest.application.global.response.HttpStateResponse;
import sigma.inbest.domain.user.User;

public interface TokenPort {
    TokenResponse issueToken(String email, String password);

    User parseToken(String Token, JwtType jwtType);

    String newAccessToken(String refreshToken);
}
