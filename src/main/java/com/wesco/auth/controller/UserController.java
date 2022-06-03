package com.wesco.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wesco.auth.constant.Constants;
import com.wesco.auth.mapper.UserMapper;
import com.wesco.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Value("${iam.provider.realm}")
    private String realm;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserMapper userMapper) throws JsonProcessingException {
        return new ResponseEntity<>(userService.create(userMapper,
                this.getKeycloakCreateUserContext(Constants.USER, realm)),HttpStatus.OK);
    }
}
