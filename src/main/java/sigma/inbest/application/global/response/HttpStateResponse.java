package sigma.inbest.application.global.response;

import jakarta.annotation.Nullable;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
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
