package com.vinhqd.app.utils;

import com.vinhqd.app.dto.MyUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityUtils {

    public List<String> getAuthorities(Authentication auth) {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
        return authorities.stream().map(item -> item.getAuthority()).collect(Collectors.toList());
    }

}
