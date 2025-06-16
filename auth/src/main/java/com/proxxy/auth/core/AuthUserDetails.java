package com.proxxy.auth.core;

import com.proxxy.auth.domain.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Getter
public class AuthUserDetails implements UserDetails {

    private final UUID id;
    private final String fullName;
    private final String email;
    private final String password;
    private final Set<GrantedAuthority> authorities;


    public AuthUserDetails(User user, Set<GrantedAuthority> authorities) {

        this.id = user.getId();
        this.fullName = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
