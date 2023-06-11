package vn.siten.base.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    private APIException() {
    }

    public static APIException from(HttpStatus httpStatus) {
        APIException ret = new APIException();
        ret.httpStatus = httpStatus;
        return ret;
    }

    public APIException withMessage(String message) {
        this.message = message;
        return this;
    }
}
