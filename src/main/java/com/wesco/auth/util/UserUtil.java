package com.wesco.auth.util;

import com.wesco.auth.constant.Constants;
import com.wesco.auth.dto.UserDetail;
import com.wesco.auth.exception.NotFoundException;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

public class UserUtil {

    public static UserDetail getCurrentUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal principal = (KeycloakPrincipal) auth.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        if (ObjectUtils.isEmpty(accessToken.getEmail()))
            throw new NotFoundException(Constants.USER_NOT_FOUND);

        return UserDetail.builder().username(accessToken.getPreferredUsername())
                .email(accessToken.getEmail()).firstName(accessToken.getGivenName())
                .lastName(accessToken.getFamilyName())
                .jwtToken(session.getTokenString())
                .build();
    }
}