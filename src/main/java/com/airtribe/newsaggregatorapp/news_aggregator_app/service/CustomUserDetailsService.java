package com.airtribe.newsaggregatorapp.news_aggregator_app.service;

import com.airtribe.newsaggregatorapp.news_aggregator_app.entity.Users;
import com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling.ResourceNotFoundException;
import com.airtribe.newsaggregatorapp.news_aggregator_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticate user details on basis of passed username
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user;
        try{
            user = userRepository.findByUsername(username);
        }
        catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Password should be encoded
                .roles("USER") // Set user roles
                .build();
    }
}
