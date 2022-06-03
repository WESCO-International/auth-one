package com.wesco.auth.controller;

import com.wesco.auth.constant.Constants;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController {

    @Value("${iam.provider.url}")
    private String keycloak_url;



    public String getKeycloakContext(String roleType, String realm) {
        String url = "";
        if (Constants.ADMIN.equalsIgnoreCase(roleType)) {
            url = keycloak_url + Constants.ADMIN_REALMS_URL + realm + Constants.ACCESS_TOKEN;
        } else if (Constants.USER.equalsIgnoreCase(roleType)) {
            url = keycloak_url + Constants.AUTH_REALMS_URL + realm + Constants.ACCESS_TOKEN;
        }
        return url;
    }
    public String getKeycloakCreateUserContext(String roleType, String realm) {
        String url = "";
        if (Constants.ADMIN.equalsIgnoreCase(roleType)) {
            url = keycloak_url + Constants.ADMIN_REALMS_URL + realm + Constants.ACCESS_TOKEN;
        } else if (Constants.USER.equalsIgnoreCase(roleType)) {
            url = keycloak_url + Constants.ADMIN_REALMS_URL + realm + Constants.USERS;
        }
        return url;
    }

}
