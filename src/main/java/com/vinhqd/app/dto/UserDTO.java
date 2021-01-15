package com.vinhqd.app.dto;

import com.vinhqd.app.anotations.PasswordMatches;
import com.vinhqd.app.anotations.ValidUserName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches(message = "Mật khẩu không chính xác")
public class UserDTO {


    @NotNull(message = "Mật khẩu phải khác null.")
    @NotEmpty(message = "Vui lòng nhập mật khẩu.")
    private String password;
    private String matchingPassword;

    @NotNull(message = "Tài khoản phải khác null.")
    @NotEmpty(message = "Vui lòng nhập tài khoản.")
    @ValidUserName(message = "Tài khoản không chính xác.")
    private String userName;

    @NotNull(message = "Tên phải khác null.")
    @NotEmpty(message = "Vui lòng nhập tên.")
    private String fullName;

    private int status;
    private String photo;
    private String roleName;
    private String roleId;

}
