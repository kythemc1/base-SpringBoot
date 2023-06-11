package co.siten.osp.resource.config.auth;

import org.springframework.security.core.userdetails.User;

public class Commons {

    public static final String LOGIN_URL = "/auth/keycloak/login";
    public static final String REFRESH_TOKEN_URL = "/auth/keycloak/refresh-token";
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_SECRET = "secret";
    public static final long TOKEN_EXPIRATION_TIME = 7*24*60*60*1000;

    public static final String[] PUBLIC_URLs = new String[]{
            "/", LOGIN_URL,
            REFRESH_TOKEN_URL, "/swagger-ui.html",
            "/v2/api-docs"
    };

    public static String jwtSubject(User user) {
        return user.getUsername();
    }

    public static String principal(String subject) {
        return subject;
    }

}
