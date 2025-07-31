package com.spiny.spiny_demo.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
