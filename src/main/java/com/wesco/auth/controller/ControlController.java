package com.wesco.auth.controller;

import com.wesco.auth.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("control")
@RestController
public class ControlController {

    @GetMapping
    public ResponseEntity<?> getControls(){
        System.out.println(UserUtil.getCurrentUserInfo());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
