package com.wesco.auth.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel
public class AccessTokenRequest {
    @ApiModelProperty(position = 4, required = true, value = "client id", example = "wdp-clients")
    private String client_id;
    @ApiModelProperty(position = 5, required = true, value = "client secret", example = "o5EZxLCe7aOcLvQ7r1VDj3ySh1J7Mdbj")
    private String client_secret;
    @ApiModelProperty(position = 1, required = true, value = "User email", example = "tenant@wesco.com")
    private String username;
    @ApiModelProperty(position = 2, required = true, value = "User password", example = "tenant")
    private String password;
    @ApiModelProperty(position = 3, required = true, value = "Grant type", example = "password")
    private String grant_type;
    @ApiModelProperty(position = 6, required = true, value = "Scope", example = "openid")
    private String scope;
}
