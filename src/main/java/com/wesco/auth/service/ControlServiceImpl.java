package com.wesco.auth.service;

import com.wesco.auth.dto.UserDetail;
import com.wesco.auth.mapper.ControlMapper;
import com.wesco.auth.repository.ControlRepository;
import com.wesco.auth.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ControlServiceImpl implements ControlService {

    @Autowired
    private ControlRepository controlRepository;

    @Override
    public List<ControlMapper> getMenu() {
        Optional<UserDetail> userDetail = UserUtil.getCurrentUserInfo();
        if(userDetail.isEmpty())
            return new ArrayList<>();
        return controlRepository.findControlsByEmail(userDetail.get().getEmail());
    }
}
