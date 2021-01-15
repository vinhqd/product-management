package com.vinhqd.app.service.impl;

import com.vinhqd.app.component.ModelConverter;
import com.vinhqd.app.constants.SystemConstants;
import com.vinhqd.app.dto.UserDTO;
import com.vinhqd.app.entity.UserEntity;
import com.vinhqd.app.exceptions.UserAlreadyExistException;
import com.vinhqd.app.repository.UserRepository;
import com.vinhqd.app.service.IUserService;
import com.vinhqd.app.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelConverter converter;

    @Autowired
    private UserUtils userUtils;

    @Override
    public UserDTO findUserByUserName(String userName) {
        return converter.toDTO(UserDTO.class, userRepository.findOneByUserName(userName));
    }

    @Transactional
    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userNameExists(userDTO.getUserName())) {
            throw new UserAlreadyExistException(
                    "There is an account with that email address: " + userDTO.getUserName());
        }
        UserEntity userEntity = converter.toEntity(UserEntity.class, userDTO);
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRoles(userUtils.getUserRole());
        userEntity.setStatus(SystemConstants.ACTIVE_STATUS);
        userEntity = userRepository.save(userEntity);
        return converter.toDTO(UserDTO.class, userEntity);
    }

    public boolean userNameExists(String userName) {
        return userRepository.findOneByUserName(userName) != null;
    }
}
