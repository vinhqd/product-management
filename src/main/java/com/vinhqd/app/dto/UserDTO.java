package com.vinhqd.app.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String userName;
    private String password;
    private String confirmPassword;
    private String fullName;
    private int status;
    private String photo;
    private String roleName;
    private String roleId;

}
