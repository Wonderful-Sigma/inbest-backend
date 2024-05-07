package sigma.inbest.application.global.response;

import org.springframework.http.HttpStatus;

public class InbestException extends RuntimeException{

    private final HttpStatus status;
    private final String message;

    public InbestException(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }
}
