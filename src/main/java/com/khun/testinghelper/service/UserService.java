package com.khun.testinghelper.service;

import com.khun.testinghelper.domain.dto.UserRequestDto;
import com.khun.testinghelper.domain.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    void insertUser(UserRequestDto userRequestDto);
    UserResponseDto getUserByEmail(String email);
    boolean isUserExistsByEmail(String email);
    List<UserResponseDto> getAllUsers();
    void toggleUserAccount(Long id);
}
