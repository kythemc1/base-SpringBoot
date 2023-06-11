package co.siten.osp.resource.controller;

import co.siten.osp.resource.data.entity.LoginRequest;
import co.siten.osp.resource.data.keycloak.entity.KeycloakLogoutRequest;
import co.siten.osp.resource.data.keycloak.entity.KeycloakRefreshTokenRequest;
import co.siten.osp.resource.data.keycloak.entity.KeycloakTokenResponse;
import co.siten.osp.resource.service.KeycloakService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("/auth/keycloak")
@Slf4j
public class KeycloakController {

    private final KeycloakService keycloakService;

    @Autowired
    public KeycloakController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<KeycloakTokenResponse> getToken(@RequestBody LoginRequest request) {
        log.debug("REST request to login");
        return keycloakService.getToken(request);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<KeycloakTokenResponse> refreshToken(@RequestBody KeycloakRefreshTokenRequest request) {
        return keycloakService.refreshToken(request);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<KeycloakTokenResponse> logout(@RequestBody KeycloakLogoutRequest request) {
        return keycloakService.logout(request);
    }

}
