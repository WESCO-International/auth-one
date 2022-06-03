package com.wesco.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesco.auth.mapper.UserMapper;
import com.wesco.auth.util.RestTemplateUtil;
import com.wesco.auth.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplateUtil restTemplate;

    @Override
    public Map<String, String> create(UserMapper userRequest,String endpintUrl) throws JsonProcessingException {
        log.info("creating user [{}]", userRequest);

        String body = new ObjectMapper().writeValueAsString(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(1024);
       // headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Bearer ", UserUtil.getCurrentUserInfo().get("token"));

        try {
            ResponseEntity<String> responseEntity = restTemplate.postCall(endpintUrl, body, headers);
            System.out.println(responseEntity.getStatusCode());
        } catch (Exception e) {
            log.error("error while creating user [ " + e.getMessage() + "]");
        }
        return null;
    }


}
