package com.example.news_springbootbackend.service;

import com.example.news_springbootbackend.entity.SecurityUser;
import com.example.news_springbootbackend.respository.JpaUserrepository;
import com.example.news_springbootbackend.respository.Userrepository;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final JpaUserrepository userRepository;

    public JpaUserDetailsService(JpaUserrepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
