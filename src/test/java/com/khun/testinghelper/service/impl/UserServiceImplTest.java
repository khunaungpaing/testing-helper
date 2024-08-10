package com.khun.testinghelper.service.impl;

import com.khun.testinghelper.TestcontainersConfiguration;
import com.khun.testinghelper.domain.dto.UserResponseDto;
import com.khun.testinghelper.domain.entity.User;
import com.khun.testinghelper.domain.enums.Role;
import com.khun.testinghelper.repository.UserRepository;
import com.khun.testinghelper.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    private UserService underTest;
    private AutoCloseable autoCloseable;
    @Mock private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserServiceImpl(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getUserByEmail() {
        // Given
        var email = "test@test.com";
        UserResponseDto userResponseDto = new UserResponseDto(
                1, null, email, null, true, null, null);

        // When
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(userResponseDto));

        var returnValue = underTest.getUserByEmail(email);

        // Then
        verify(userRepository).findByEmail(email);
        assertThat(returnValue).isEqualTo(userResponseDto);
        assertThat("nonexist@test.com").isNotEqualTo(userResponseDto.email());
    }
}