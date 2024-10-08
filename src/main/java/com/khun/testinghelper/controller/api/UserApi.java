package com.khun.testinghelper.controller.api;

import com.khun.testinghelper.domain.dto.UserRequestDto;
import com.khun.testinghelper.domain.dto.UserResponseDto;
import com.khun.testinghelper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Validated @RequestBody UserRequestDto userRequestDto) {
        userService.insertUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User [%s] is created successfully.".formatted(userRequestDto.email()));
    }
}
