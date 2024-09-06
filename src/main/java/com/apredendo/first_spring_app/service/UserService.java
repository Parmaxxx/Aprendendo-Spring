package com.apredendo.first_spring_app.service;

import com.apredendo.first_spring_app.dto.CreateUserDTO;
import com.apredendo.first_spring_app.dto.JwtTokenDTO;
import com.apredendo.first_spring_app.dto.LoginUserDTO;
import com.apredendo.first_spring_app.model.ModelRole;
import com.apredendo.first_spring_app.model.ModelUser;
import com.apredendo.first_spring_app.model.ModelUserDetailsImpl;
import com.apredendo.first_spring_app.repository.UserRepository;
import com.apredendo.first_spring_app.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;

    public void salvarUsuario(CreateUserDTO createUserDTO) {
        ModelUser newUser = ModelUser.builder()
                .email(createUserDTO.email())
                .password(securityConfig.passwordEncoder().encode(createUserDTO.password()))
                .roles(List.of(ModelRole.builder().name(createUserDTO.role()).build()))
                .build();

        userRepository.save(newUser);
    }

    public JwtTokenDTO authenticarUsuario(LoginUserDTO loginUserDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDTO.email(),
                        loginUserDTO.password());

        Authentication authentication = authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);
        ModelUserDetailsImpl modelUserDetails = (ModelUserDetailsImpl) authentication
                .getPrincipal();
        return new JwtTokenDTO(jwtTokenService.generateToken(modelUserDetails));
    }
}
