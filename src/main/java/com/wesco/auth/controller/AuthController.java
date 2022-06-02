package com.wesco.auth.controller;

import com.wesco.auth.constant.Constants;
import com.wesco.auth.mapper.AccessTokenRequest;
import com.wesco.auth.mapper.AccessTokenResponse;
import com.wesco.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@Slf4j
@RequestMapping("auth")
@RestController
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @Value("${iam.provider.realm}")
    private String realm;

    @PostMapping(path = "token")
    public AccessTokenResponse getToken(@RequestBody AccessTokenRequest accessTokenRequest)
            throws AuthException {
        AccessTokenResponse accessTokenResponse = null;
        log.info("Generating access token for clientID {} ", accessTokenRequest.getClient_id());
        accessTokenResponse = authService.getAccessToken(accessTokenRequest,
                this.getKeycloakContext(Constants.USER, realm));
        return accessTokenResponse;
    }
}
