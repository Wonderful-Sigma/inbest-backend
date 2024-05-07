package sigma.inbest.adapter.http.auth.request;

import sigma.inbest.application.auth.interacter.response.TokenResponse;

public record SimpleLogInRequest(TokenResponse tokenResponse, String simplePassword) {
}
