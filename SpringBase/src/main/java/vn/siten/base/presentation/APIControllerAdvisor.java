package vn.siten.base.presentation;

import vn.siten.base.core.exception.APIException;
import vn.siten.base.presentation.entity.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class APIControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponse> handleAPIException(APIException e, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.fromException(e), e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception e, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.fromException(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
