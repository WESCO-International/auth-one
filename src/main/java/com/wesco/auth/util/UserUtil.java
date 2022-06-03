package com.wesco.auth.util;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;


public class UserUtil {

    public static Map<String, String> getCurrentUserInfo() {
        Map<String, String> map = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal principal = (KeycloakPrincipal) auth.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();

        String username = accessToken.getPreferredUsername();
        String emailID = accessToken.getEmail();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        String realmName = accessToken.getIssuer();
        AccessToken.Access realmAccess = accessToken.getRealmAccess();
        map.put("email", emailID);
        /*map.put("token", principal.getKeycloakSecurityContext().getTokenString());*/
        map.put("token",
                "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJiYWRiODRlNi03ODUyLTRhNGYtOWRmNC1lZTM2MDYxYmY4ZTQifQ.eyJleHAiOjE2NTQyMTE3MzUsImlhdCI6MTY1NDE3NTczNSwianRpIjoiZWMzZjMzNjMtMjBhOS00NTE2LThjMTAtNGFmMGY3NzVjY2IyIiwiaXNzIjoiaHR0cHM6Ly9jb21tb24taWFtLXNlcnZpY2UuZ2FsdWN0dXNzYW5kYm94LmFwcC9yZWFsbXMvbWFzdGVyIiwic3ViIjoiN2FkNzQ3YmMtN2RjYS00Y2Q3LTk5NWUtZDgyNmQ4ODExMzViIiwidHlwIjoiU2VyaWFsaXplZC1JRCIsInNlc3Npb25fc3RhdGUiOiI3NmQyYjgwNi1kZWVlLTQ5M2YtOGQzZi0xYzM3ZjRmODY3ODIiLCJzaWQiOiI3NmQyYjgwNi1kZWVlLTQ5M2YtOGQzZi0xYzM3ZjRmODY3ODIiLCJzdGF0ZV9jaGVja2VyIjoieHdSdzhTUFo4QlBwdkpJeG8zcnRCWHBjSlRQVjJNcUJmWHhDUGY2bHhBYyJ9.Gx7B4sC7VlmN-Vb7E1x5EIJaF27AQbP3qQPZHlBf67g; KEYCLOAK_IDENTITY_LEGACY=eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJiYWRiODRlNi03ODUyLTRhNGYtOWRmNC1lZTM2MDYxYmY4ZTQifQ.eyJleHAiOjE2NTQyMTE3MzUsImlhdCI6MTY1NDE3NTczNSwianRpIjoiZWMzZjMzNjMtMjBhOS00NTE2LThjMTAtNGFmMGY3NzVjY2IyIiwiaXNzIjoiaHR0cHM6Ly9jb21tb24taWFtLXNlcnZpY2UuZ2FsdWN0dXNzYW5kYm94LmFwcC9yZWFsbXMvbWFzdGVyIiwic3ViIjoiN2FkNzQ3YmMtN2RjYS00Y2Q3LTk5NWUtZDgyNmQ4ODExMzViIiwidHlwIjoiU2VyaWFsaXplZC1JRCIsInNlc3Npb25fc3RhdGUiOiI3NmQyYjgwNi1kZWVlLTQ5M2YtOGQzZi0xYzM3ZjRmODY3ODIiLCJzaWQiOiI3NmQyYjgwNi1kZWVlLTQ5M2YtOGQzZi0xYzM3ZjRmODY3ODIiLCJzdGF0ZV9jaGVja2VyIjoieHdSdzhTUFo4QlBwdkpJeG8zcnRCWHBjSlRQVjJNcUJmWHhDUGY2bHhBYyJ9.Gx7B4sC7VlmN-Vb7E1x5EIJaF27AQbP3qQPZHlBf67g");
        return map;
    }
}

