package com.etiya.etiya.security;

import com.etiya.etiya.entity.User;
import com.etiya.etiya.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.emailBul(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No User found with username '%s'.", username));
        }else {
            return JwtUserFactory.create(user);
        }
    }

}