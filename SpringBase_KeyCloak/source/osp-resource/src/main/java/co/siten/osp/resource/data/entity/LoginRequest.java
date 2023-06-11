package co.siten.osp.resource.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequest {

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

}
