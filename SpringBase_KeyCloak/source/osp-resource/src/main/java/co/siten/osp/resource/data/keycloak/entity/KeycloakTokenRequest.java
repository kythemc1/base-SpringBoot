package co.siten.osp.resource.data.keycloak.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KeycloakTokenRequest {
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
