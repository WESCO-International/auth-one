package com.wesco.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesco.auth.config.RestTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.message.AuthException;
import java.util.Map;

@Slf4j
@Component
public class RestTemplateUtil {


    @Autowired
    private RestTemplateConfig restTemplate;

    public ResponseEntity<String> postCall(String apiEndpoint, String body, HttpHeaders httpHeaders)
            throws AuthException, JsonMappingException, JsonProcessingException {
        log.info("Calling API {} ", apiEndpoint);

        Map<String, Object> map = new ObjectMapper().readValue(body, new TypeReference<Map<String, Object>>() {
        });
        map.remove("realm");
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<String, Object>();
        valueMap.setAll(map);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
                valueMap, httpHeaders);
        log.debug("***** requestEntity.getBody() : {}", requestEntity.getBody());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiEndpoint, HttpMethod.POST, requestEntity,
                String.class);

        return response;
    }
    
}
