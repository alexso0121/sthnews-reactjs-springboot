package com.example.news_springbootbackend.Security;

import com.example.news_springbootbackend.entity.SecurityUser;
import com.example.news_springbootbackend.respository.JpaUserrepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


//security for authentication
//load the user in the database
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final JpaUserrepository userRepository;

    public JpaUserDetailsService(JpaUserrepository userRepository) {
        this.userRepository = userRepository;
    }

    //main method for getting the users in the database and make them authenticated
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                //security user entity is based on the implementation of userdetail for authenticate the spring boot security
                .map(SecurityUser::new)
                //throw if no matchers found
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
