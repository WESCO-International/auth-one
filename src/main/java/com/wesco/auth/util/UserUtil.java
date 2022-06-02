package com.wesco.auth.util;

import com.wesco.auth.dto.UserDetail;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserUtil {

    public static Optional<UserDetail> getCurrentUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal principal = (KeycloakPrincipal)auth.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        UserDetail userDetail = UserDetail.builder().username(accessToken.getPreferredUsername())
                .email(accessToken.getEmail()).firstName(accessToken.getGivenName())
                .lastName(accessToken.getFamilyName()).build();
        return Optional.of(userDetail);
    }
}
