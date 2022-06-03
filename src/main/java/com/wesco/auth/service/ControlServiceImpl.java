package com.wesco.auth.service;

import com.wesco.auth.mapper.CapabilitiesMapper;
import com.wesco.auth.mapper.ControlMapper;
import com.wesco.auth.repository.ControlRepository;
import com.wesco.auth.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ControlServiceImpl implements ControlService {

    @Autowired
    private ControlRepository controlRepository;

    @Override
    public List<ControlMapper> getMenu() {
        log.info("fetch menu for user {} ",UserUtil.getCurrentUserInfo().getEmail());
        return controlRepository.findUserControls(UserUtil.getCurrentUserInfo().getEmail());
    }

    @Override
    public List<CapabilitiesMapper> getCapabilities() {
        log.info("fetch capabilities for user {} ",UserUtil.getCurrentUserInfo().getEmail());
        return controlRepository.findUserCapabilities(UserUtil.getCurrentUserInfo().getEmail());
    }
}
