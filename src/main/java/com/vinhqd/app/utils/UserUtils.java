package com.vinhqd.app.utils;

import com.vinhqd.app.constants.SystemConstants;
import com.vinhqd.app.entity.RoleEntity;
import com.vinhqd.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserUtils {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleEntity> getUserRole() {
        RoleEntity userRole = roleRepository.findOneByCode(SystemConstants.USER_ROLE);
        return Arrays.asList(new RoleEntity[]{userRole});
    }

}
