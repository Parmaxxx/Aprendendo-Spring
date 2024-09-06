package com.apredendo.first_spring_app.controller;

import com.apredendo.first_spring_app.dto.CreateUserDTO;
import com.apredendo.first_spring_app.dto.JwtTokenDTO;
import com.apredendo.first_spring_app.dto.LoginUserDTO;
import com.apredendo.first_spring_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> loginUsuario(@RequestBody LoginUserDTO loginUserDTO) {
        JwtTokenDTO token = userService.authenticarUsuario(loginUserDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody CreateUserDTO createUserDTO) {
        userService.salvarUsuario(createUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
