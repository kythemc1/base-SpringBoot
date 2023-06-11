package co.siten.osp.resource.data.keycloak;

import co.siten.osp.resource.config.KeycloakSettings;
import co.siten.osp.resource.data.keycloak.entity.KeycloakLogoutRequest;
import co.siten.osp.resource.data.keycloak.entity.KeycloakTokenResponse;
import co.siten.osp.resource.data.repository.KeycloakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class KeycloakApi implements KeycloakRepository {

    private KeycloakSettings keycloakSettings;

    private RestTemplate restTemplate;


    @Autowired
    public KeycloakApi(KeycloakSettings keycloakSettings,  RestTemplateBuilder builder) {
        this.keycloakSettings = keycloakSettings;
        this.restTemplate = builder.build();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("Content-Type", "application/x-www-form-urlencoded");
            return execution.execute(request, body);
        });
    }

    @Override
    public ResponseEntity<KeycloakTokenResponse> getToken(MultiValueMap<String, Object> request) {
        return restTemplate.postForEntity(getUrl(this.keycloakSettings.getGetToken()), new HttpEntity<>(request), KeycloakTokenResponse.class);
    }

    @Override
    public ResponseEntity<KeycloakTokenResponse> logout(MultiValueMap<String, Object> request) {
        return restTemplate.postForEntity(getUrl("logout"), new HttpEntity<>(request), KeycloakTokenResponse.class);
    }

    private String getUrl(String path) {
        return this.keycloakSettings.getBaseUrl() + path;
    }
}
