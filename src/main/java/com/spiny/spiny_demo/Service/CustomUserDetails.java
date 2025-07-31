package com.spiny.spiny_demo.Service;

import com.spiny.spiny_demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Add "ROLE_" prefix as Spring expects
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );
    }


    @Override
    public String getPassword() {
        return user.getPassword(); // Return actual password
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Return actual username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can customize this based on your needs
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
