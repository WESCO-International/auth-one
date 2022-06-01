package com.wesco.auth.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AccessTokenRequest {
    
    private String client_id;
    private String client_secret;
    private String username;
    private String password;
    private String grant_type;
    private String scope;

}
