package com.vinhqd.app.service;

import com.vinhqd.app.dto.UserDTO;

public interface IUserService {

    UserDTO findUserByUserName(String userName);

    UserDTO save(UserDTO userDTO);

}
