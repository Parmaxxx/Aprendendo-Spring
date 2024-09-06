package com.apredendo.first_spring_app.service;

import com.apredendo.first_spring_app.model.ModelUser;
import com.apredendo.first_spring_app.model.ModelUserDetailsImpl;
import com.apredendo.first_spring_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
   @Autowired
   private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ModelUser modelUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new ModelUserDetailsImpl(modelUser);
    }
}
