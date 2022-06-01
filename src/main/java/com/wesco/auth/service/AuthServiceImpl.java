package com.wesco.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesco.auth.mapper.AccessTokenRequest;
import com.wesco.auth.mapper.AccessTokenResponse;
import com.wesco.auth.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

@Slf4j
@Service
public class AuthServiceImpl implements  AuthService{

    @Autowired
    private RestTemplateUtil restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public AccessTokenResponse getAccessToken(AccessTokenRequest accessTokenRequest, String endPointurl) {
        log.info("Genarating Access token for client {} ", accessTokenRequest.getClient_id());
        String body;
        String reponseBody;
        AccessTokenResponse accessTokenResponse = null;
        try {
            body = new ObjectMapper().writeValueAsString(accessTokenRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(1024);
            headers.add("Content-Type", "application/x-www-form-urlencoded");
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            ResponseEntity<String> responseEntity = restTemplate.postCall(endPointurl, body, headers);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                reponseBody = responseEntity.getBody();
                accessTokenResponse =  objectMapper.readValue(reponseBody, AccessTokenResponse.class);
            }

        } catch (JsonProcessingException | AuthException e) {
            e.printStackTrace();
        }
        return accessTokenResponse;
    }
}
