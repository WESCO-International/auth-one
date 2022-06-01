package com.wesco.auth.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AccessTokenResponse {

    private String access_token;
    private int expires_in;
    private int refresh_expires_in;
    private String refresh_token;
    private String token_type;
    private String id_token;

    @JsonProperty("not-before-policy")
    private int notBeforepolicy;
    private String session_stateString;
    private String scope;
}
