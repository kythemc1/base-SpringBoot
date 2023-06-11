package vn.siten.base.core.auth;

import org.springframework.security.core.userdetails.User;

public class Commons {

    public static String jwtSubject(User user) {
        return user.getUsername();
    }

    public static String principal(String subject) {
        return subject;
    }

}
