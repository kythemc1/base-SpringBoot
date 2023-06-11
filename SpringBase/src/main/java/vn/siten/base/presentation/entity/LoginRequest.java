package vn.siten.base.presentation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginRequest {

    @JsonProperty("id") private String username;
    @JsonProperty("password") private String password;

}
