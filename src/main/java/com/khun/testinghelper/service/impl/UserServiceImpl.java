package com.khun.testinghelper.service.impl;

import com.khun.testinghelper.domain.dto.UserRequestDto;
import com.khun.testinghelper.domain.dto.UserResponseDto;
import com.khun.testinghelper.domain.entity.User;
import com.khun.testinghelper.domain.enums.Role;
import com.khun.testinghelper.exception.ResourceConflictException;
import com.khun.testinghelper.exception.ResourceNotFoundException;
import com.khun.testinghelper.repository.UserRepository;
import com.khun.testinghelper.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto insertUser(UserRequestDto userRequestDto) {
        if(isUserExistsByEmail(userRequestDto.email())){
            throw new ResourceConflictException("User [%s] already taken".formatted(userRequestDto.email()));
        }
        User user = new User();
        user.setName(userRequestDto.name());
        user.setEmail(userRequestDto.email());
        user.setPassword(userRequestDto.password());
        user.setRole(Role.MEMBER);
        user.setActive(true);
        userRepository.save(user);

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.isActive(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User [%s] not found.".formatted(email)));
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly=true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAllByOrderByNameAsc();
    }

    @Override
    public void toggleUserAccount(Long id) {
        var user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User [%s] not found.".formatted(id)));
        user.setActive(!user.isActive());
        userRepository.save(user);
    }

}
