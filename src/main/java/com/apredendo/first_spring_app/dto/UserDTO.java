package com.apredendo.first_spring_app.dto;

import com.apredendo.first_spring_app.enums.Role;

import java.util.List;

public record UserDTO(Long id, String email, List<Role> roles) {
}
