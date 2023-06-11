package co.siten.osp.resource.service;

import co.siten.osp.resource.config.KeycloakSettings;
import co.siten.osp.resource.constant.Constant;
import co.siten.osp.resource.data.entity.LoginRequest;
import co.siten.osp.resource.data.keycloak.entity.KeycloakLogoutRequest;
import co.siten.osp.resource.data.keycloak.entity.KeycloakRefreshTokenRequest;
import co.siten.osp.resource.data.keycloak.entity.KeycloakTokenResponse;
import co.siten.osp.resource.data.repository.KeycloakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class KeycloakService {

    private KeycloakSettings keycloakSettings;

    private KeycloakRepository keycloakRepository;

    @Autowired
    public KeycloakService(KeycloakRepository keycloakRepository, KeycloakSettings keycloakSettings) {
        this.keycloakRepository = keycloakRepository;
        this.keycloakSettings = keycloakSettings;
    }

    public ResponseEntity<KeycloakTokenResponse> getToken(LoginRequest loginRequest) {
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(Constant.FIELD_CLIENT_ID, keycloakSettings.getResource());
        requestBody.add(Constant.FIELD_GRANT_TYPE, keycloakSettings.getGrantType());
        requestBody.add(Constant.FIELD_CLIENT_SECRET, keycloakSettings.getSecret());
        requestBody.add(Constant.FIELD_USERNAME, loginRequest.getUsername());
        requestBody.add(Constant.FIELD_PASSWORD, loginRequest.getPassword());
        return keycloakRepository.getToken(requestBody);
    }

    public ResponseEntity<KeycloakTokenResponse> refreshToken(KeycloakRefreshTokenRequest refreshTokenRequest) {
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(Constant.FIELD_CLIENT_ID, keycloakSettings.getResource());
        requestBody.add(Constant.FIELD_GRANT_TYPE, keycloakSettings.getRefreshGrantType());
        requestBody.add(Constant.FIELD_CLIENT_SECRET, keycloakSettings.getSecret());
        requestBody.add(Constant.FIELD_REFRESH_TOKEN, refreshTokenRequest.getRefreshToken());
        return keycloakRepository.getToken(requestBody);
    }

    public ResponseEntity<KeycloakTokenResponse> logout(KeycloakLogoutRequest keycloakLogoutRequest) {
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(Constant.FIELD_CLIENT_ID, keycloakSettings.getResource());
        requestBody.add(Constant.FIELD_CLIENT_SECRET, keycloakSettings.getSecret());
        requestBody.add(Constant.FIELD_REFRESH_TOKEN, keycloakLogoutRequest.getRefreshToken());
        return keycloakRepository.logout(requestBody);
    }
}
