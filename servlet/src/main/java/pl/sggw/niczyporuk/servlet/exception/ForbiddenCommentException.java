package pl.sggw.niczyporuk.servlet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenCommentException extends RuntimeException {

    public ForbiddenCommentException(final String message) {
        super(message);
    }
}