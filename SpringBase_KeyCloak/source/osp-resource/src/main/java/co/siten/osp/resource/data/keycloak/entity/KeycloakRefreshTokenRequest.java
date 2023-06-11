package co.siten.osp.resource.data.keycloak.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KeycloakRefreshTokenRequest {

    @JsonProperty("refresh_token")
    private String refreshToken;

}
