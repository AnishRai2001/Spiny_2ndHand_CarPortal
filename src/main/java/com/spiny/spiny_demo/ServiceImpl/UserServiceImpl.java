package com.spiny.spiny_demo.ServiceImpl;

import com.spiny.spiny_demo.Dto.LoginRequest;
import com.spiny.spiny_demo.Dto.RegisterUser;
import com.spiny.spiny_demo.Exception.EmailAlreadyExistsException;
import com.spiny.spiny_demo.Exception.PhoneAlreadyExistsException;
import com.spiny.spiny_demo.Exception.UsernameAlreadyExistsException;
import com.spiny.spiny_demo.Repository.UserRepository;
import com.spiny.spiny_demo.Service.UserService;
import com.spiny.spiny_demo.entity.User;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User RegisterUser(RegisterUser registerUser) {

        if (userRepository.findByEmail(registerUser.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        if (userRepository.findByUsername(registerUser.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        if (userRepository.findByPhone(registerUser.getPhone()).isPresent()) {
            throw new PhoneAlreadyExistsException("Phone already exists");
        }

        User user = new User();
        BeanUtils.copyProperties(registerUser, user);

        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email is required");
        }

        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User LoginUser(LoginRequest loginRequest) {
        throw new UnsupportedOperationException("Login not yet implemented");
    }
}
