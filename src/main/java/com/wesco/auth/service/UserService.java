package com.wesco.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesco.auth.mapper.UserMapper;


import java.util.Map;

public interface UserService {
    Map<String,String> create(UserMapper userRequest,String endpointUrl) throws JsonProcessingException;
}
