package co.siten.osp.resource.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties
@Configuration
public class KeycloakSettings {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${settings.keycloak.base-url}")
    private String baseUrl;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${settings.keycloak.user-info}")
    private String userInfo;

    @Value("${settings.keycloak.token.get}")
    private String getToken;

    @Value("${settings.keycloak.token.introspect}")
    private String introspect;

    @Value("${keycloak.credentials.secret}")
    private String secret;

    @Value("${keycloak.resource}")
    private String resource;

    @Value("${settings.keycloak.grant-type}")
    private String grantType;

    @Value("${settings.keycloak.refresh-token.grant-type}")
    private String refreshGrantType;

    @Value("${settings.keycloak.root-user.username}")
    private String rootUsername;

    @Value("${settings.keycloak.root-user.password}")
    private String rootPassword;

}
