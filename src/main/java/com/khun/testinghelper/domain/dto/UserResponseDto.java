package com.khun.testinghelper.domain.dto;

import com.khun.testinghelper.domain.entity.User;
import com.khun.testinghelper.domain.enums.Role;

import java.sql.Timestamp;

public record UserResponseDto(
        long id,
        String name,
        String email,
        Role role,
        boolean active,
        Timestamp createdDate,
        Timestamp updatedDate
) {
}
