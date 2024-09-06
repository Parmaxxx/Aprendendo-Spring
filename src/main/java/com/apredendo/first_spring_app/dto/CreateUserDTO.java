package com.apredendo.first_spring_app.dto;

import com.apredendo.first_spring_app.enums.Role;

public record CreateUserDTO(String email, String password, Role role) {
}
