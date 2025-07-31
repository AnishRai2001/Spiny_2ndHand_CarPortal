package com.spiny.spiny_demo.Dto;

import com.spiny.spiny_demo.entity.Role;
import lombok.Data;

@Data
public class RegisterUser {
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;

    private Role role;
}
