package vn.siten.base.presentation.entity;

import vn.siten.base.data.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collection;

@Data
public class RegisterUserRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("first-name")
    private String firstName;


    @JsonProperty("last-name")
    private String lastName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("roles")
    private Collection<Role> roles;
}
