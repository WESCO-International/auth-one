package com.wesco.auth.service;

import com.wesco.auth.mapper.CapabilitiesMapper;
import com.wesco.auth.mapper.ControlMapper;
import com.wesco.auth.repository.ControlRepository;
import com.wesco.auth.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlServiceImpl implements ControlService {

    @Autowired
    private ControlRepository controlRepository;

    @Override
    public List<ControlMapper> getMenu() {
        return controlRepository.findUserControls(UserUtil.getCurrentUserInfo().getEmail());
    }

    @Override
    public List<CapabilitiesMapper> getCapabilities() {
        return controlRepository.findUserCapabilities(UserUtil.getCurrentUserInfo().getEmail());
    }
}
