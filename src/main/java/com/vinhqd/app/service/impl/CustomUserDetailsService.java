package com.vinhqd.app.service.impl;

import com.vinhqd.app.constants.SystemConstants;
import com.vinhqd.app.dto.MyUser;
import com.vinhqd.app.entity.UserEntity;
import com.vinhqd.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstants.ACTIVE_STATUS);
        if (userEntity == null) throw new UsernameNotFoundException("User not found!");
        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(item -> new SimpleGrantedAuthority(item.getCode())).collect(Collectors.toList());
        MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(), true,
                true, true, true, authorities);
        myUser.setFullName(userEntity.getFullName());
        myUser.setPhoto(userEntity.getPhoto());
        return myUser;
    }

}
