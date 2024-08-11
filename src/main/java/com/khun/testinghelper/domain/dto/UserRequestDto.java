package com.khun.testinghelper.domain.dto;

import com.khun.testinghelper.domain.enums.Role;

import java.sql.Timestamp;

public record UserRequestDto(
        String name,
        String email,
        String password
) {
}
