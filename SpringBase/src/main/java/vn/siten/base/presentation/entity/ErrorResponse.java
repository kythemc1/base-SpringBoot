package vn.siten.base.presentation.entity;

import vn.siten.base.core.exception.APIException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    @JsonProperty("status_code") private Integer statusCode;
    @JsonProperty("message") private String message;

    public static ErrorResponse fromException(APIException e) {
        ErrorResponse ret = new ErrorResponse();
        ret.statusCode = e.getHttpStatus().value();
        ret.message = e.getMessage();
        return ret;
    }

    public static ErrorResponse fromException(Exception e) {
        ErrorResponse ret = new ErrorResponse();
        ret.statusCode = 500;
        ret.message = e.getMessage();
        return ret;
    }
}
