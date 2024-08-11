package com.khun.testinghelper.domain.dto;


import com.khun.testinghelper.exception.BadRequestException;

public record UserRequestDto(
        String name,
        String email,
        String password
) {
    public UserRequestDto {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Name cannot be blank");
        }
        if (email == null || email.isBlank()) {
            throw new BadRequestException("Email cannot be blank");
        }
        if (password == null || password.isBlank()) {
            throw new BadRequestException("Password cannot be blank");
        }
    }
}
