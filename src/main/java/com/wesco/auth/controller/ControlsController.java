package com.wesco.auth.controller;

import com.wesco.auth.mapper.CapabilitiesMapper;
import com.wesco.auth.mapper.ControlMapper;
import com.wesco.auth.repository.UserRepository;
import com.wesco.auth.service.ControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("controls")
public class ControlsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ControlService controlService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/menu")
    public ResponseEntity<List<ControlMapper>> getMenu(){
        List<ControlMapper> controlMappers = controlService.getMenu();
        return new ResponseEntity<>(controlMappers, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/capabilities")
    public ResponseEntity<Map<String, List<String>>> getControlsAndCapabilities(){
        List<ControlMapper> controlMappers = controlService.getMenu();
        List<CapabilitiesMapper> capabilitiesMappers = controlService.getCapabilities();
        List<String> controls = controlMappers.stream().map(ControlMapper::getName).collect(Collectors.toList());
        List<String> capabilities = capabilitiesMappers.stream().map(CapabilitiesMapper::getName).collect(Collectors.toList());
        return new ResponseEntity<>(Map.of("controls", controls, "capabilities", capabilities), HttpStatus.OK);
    }
}
