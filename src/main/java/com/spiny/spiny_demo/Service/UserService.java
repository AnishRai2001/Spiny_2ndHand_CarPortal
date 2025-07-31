package com.spiny.spiny_demo.Service;

import com.spiny.spiny_demo.Dto.LoginRequest;
import com.spiny.spiny_demo.Dto.RegisterUser;
import com.spiny.spiny_demo.entity.User;

import java.util.Optional;

public interface UserService {


    public User RegisterUser (RegisterUser registerUser) ;
    public User LoginUser(LoginRequest loginRequest) ;


}
