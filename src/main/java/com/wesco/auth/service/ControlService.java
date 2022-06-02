package com.wesco.auth.service;

import com.wesco.auth.mapper.ControlMapper;

import java.util.List;

public interface ControlService {
    List<ControlMapper> getMenu();
}
