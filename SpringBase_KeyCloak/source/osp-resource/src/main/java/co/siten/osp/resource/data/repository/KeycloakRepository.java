package co.siten.osp.resource.data.repository;

import co.siten.osp.resource.data.keycloak.entity.KeycloakTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface KeycloakRepository {
    ResponseEntity<KeycloakTokenResponse> getToken(MultiValueMap<String, Object> request);

    ResponseEntity<KeycloakTokenResponse> logout(MultiValueMap<String, Object> request);

}
