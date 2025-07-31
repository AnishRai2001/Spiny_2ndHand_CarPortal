package com.spiny.spiny_demo.Controller;

import com.spiny.spiny_demo.Dto.LoginRequest;
import com.spiny.spiny_demo.Dto.RegisterUser;
import com.spiny.spiny_demo.Service.UserService;
import com.spiny.spiny_demo.Structure.ResponseStructure;
import com.spiny.spiny_demo.config.JwtService;
import com.spiny.spiny_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> registerUser(@RequestBody RegisterUser registerUser) {
        User user = userService.RegisterUser(registerUser);
        ResponseStructure<User> response = new ResponseStructure<>();
        response.setMessage("Register Success");
        response.setData(user);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<String>> login(@RequestBody LoginRequest request) {
        ResponseStructure<String> response = new ResponseStructure<>();
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            String token = jwtService.generateToken(request.getEmail(), auth.getAuthorities().iterator().next().getAuthority());

            response.setMessage("Login Success");
            response.setData(token);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            response.setMessage("Invalid username or password");
            response.setSuccess(false);
            return ResponseEntity.status(401).body(response);
        }
    }
}
