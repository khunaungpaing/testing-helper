package com.khun.testinghelper.service.impl;

import com.khun.testinghelper.domain.dto.UserResponseDto;
import com.khun.testinghelper.exception.ResourceNotFoundException;
import com.khun.testinghelper.repository.UserRepository;
import com.khun.testinghelper.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User [%s] not found.".formatted(email)));
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return false;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return List.of();
    }

    @Override
    public boolean disableUser(String email) {
        return false;
    }

    @Override
    public boolean enableUser(String email) {
        return false;
    }
}
