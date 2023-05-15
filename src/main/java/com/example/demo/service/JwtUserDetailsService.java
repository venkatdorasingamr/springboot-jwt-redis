package com.example.demo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtUserDetails;
import com.example.demo.entity.UserInfo;
import com.example.demo.repo.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final UserInfo client = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found"));
        System.out.println(client.getUsername()+client.getPassword());
        return new JwtUserDetails(client.getId(), username, client.getHash(), Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
    }

}