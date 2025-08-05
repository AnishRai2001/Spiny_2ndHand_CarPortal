package com.spiny.spiny_demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class  JwtService{

    public static  final String SECRET_KEY = "my-super-secret-key";
    public static final long EXPIRATION_TIME = 864000000;

    public String generateToken(String username, String role) {
        return JWT.create()
                .withSubject(username)
                .withClaim("authorities", "ROLE_" + role) // ✅ renamed to 'authorities'
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validateTokenAndRetrieveSubject(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();
    }

}

