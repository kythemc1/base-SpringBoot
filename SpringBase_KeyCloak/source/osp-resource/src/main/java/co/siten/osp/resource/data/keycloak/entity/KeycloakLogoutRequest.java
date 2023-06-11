package co.siten.osp.resource.data.keycloak.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KeycloakLogoutRequest {

    @JsonProperty("refresh_token")
    private String refreshToken;

}
