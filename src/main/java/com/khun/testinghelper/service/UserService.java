package com.khun.testinghelper.service;

import com.khun.testinghelper.domain.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDto getUserByEmail(String email);
    UserResponseDto getUserByUsername(String username);
    boolean isUserExistsByEmail(String email);
    List<UserResponseDto> getAllUsers();
    boolean disableUser(String email);
    boolean enableUser(String email);
}
