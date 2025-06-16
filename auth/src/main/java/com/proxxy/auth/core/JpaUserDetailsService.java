package com.proxxy.auth.core;

import com.proxxy.auth.domain.model.User;
import com.proxxy.auth.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format("There is no registered user with email %s", email)));
        Set<GrantedAuthority> authorities = getAuthorities(user);

        return new AuthUserDetails(user, authorities);
    }

    private Set<GrantedAuthority> getAuthorities(User user) {

        Hibernate.initialize(user.getGroups());
        return user.getGroups().stream().flatMap(group -> group.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
    }
}