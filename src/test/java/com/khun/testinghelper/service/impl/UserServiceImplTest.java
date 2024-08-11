package com.khun.testinghelper.service.impl;

import com.khun.testinghelper.domain.dto.UserRequestDto;
import com.khun.testinghelper.domain.dto.UserResponseDto;
import com.khun.testinghelper.domain.entity.User;
import com.khun.testinghelper.domain.enums.Role;
import com.khun.testinghelper.exception.ResourceConflictException;
import com.khun.testinghelper.exception.ResourceNotFoundException;
import com.khun.testinghelper.repository.UserRepository;
import com.khun.testinghelper.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.*;

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
    void shouldUserResponseDtoReturnsWhenGetByEmail() {
        // Given
        var email = "test@test.com";
        UserResponseDto userResponseDto = new UserResponseDto(
                1, null, email, null, true, null, null);
        // When
        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(userResponseDto));
        var actual = underTest.getUserByEmail(email);

        // Then
        verify(userRepository).findByEmail(email);
        assertThat(actual).isEqualTo(userResponseDto);
        assertThat("nonexist@test.com").isNotEqualTo(userResponseDto.email());
    }

    @Test
    void shouldThrowsNotFoundExceptionWhenUserDoesNotExist() {
        // Given
        var email = "test@test.com";
        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> underTest.getUserByEmail(email));
        // Verify that findByEmail was called
        verify(userRepository).findByEmail(email);
    }

    @Test
    void shouldFalseReturnsWhenUserDoesNotExist() {
        // Given
        var email = "nonexist@test.com";
        // When
        var actual = underTest.isUserExistsByEmail(email);
        // Then
        verify(userRepository).existsByEmail(email);
        assertThat(actual).isFalse();
    }

    @Test
    void shouldTrueReturnsWhenUserExist() {
        // Given
        var email = "test@test.com";
        // When
        when(userRepository.existsByEmail(email)).thenReturn(true);
        var actual = underTest.isUserExistsByEmail(email);
        // Then
        verify(userRepository).existsByEmail(email);
        assertThat(actual).isTrue();
    }

    @Test
    void shouldToggleUserWhenUserIdExists(){
        // Given
        var id = 1L;
        var user = new User();
        user.setActive(true);

        // When
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        underTest.toggleUserAccount(id);

        // Then
        verify(userRepository).findById(id);
        verify(userRepository).save(user);
    }

    @Test
    void shouldThrowsNotFoundExceptionWhenToggleUserByUserIdThatDoesNotExist(){
        // Given
        var id = 1L;
        var user = new User();
        user.setActive(true);

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> underTest.toggleUserAccount(id));
        verify(userRepository).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        // Given
        var email = "john.doe@example.com";
        var userRequestDto = new UserRequestDto("John Doe", email, "password123");

        when(underTest.isUserExistsByEmail(email)).thenReturn(true);

        // When & Then
        assertThrows(ResourceConflictException.class, () -> underTest.insertUser(userRequestDto));

        // Verify that save method is never called
        verify(userRepository, never()).save(new User());
    }

    @Test
    void shouldSaveUserWhenEmailIsNotTaken() {
        // Given
        var userRequestDto = new UserRequestDto("John Doe", "john.doe@example.com", "password123");

        when(underTest.isUserExistsByEmail(userRequestDto.email())).thenReturn(false);

        // When
        underTest.insertUser(userRequestDto);

        // Then
        ArgumentCaptor<User> userArgumentCaptor = forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertEquals("John Doe", capturedUser.getName());
        assertEquals("john.doe@example.com", capturedUser.getEmail());
        assertEquals("password123", capturedUser.getPassword());
        assertEquals(Role.MEMBER, capturedUser.getRole());
        assertTrue(capturedUser.isActive());
    }

    @Test
    void shouldReturnAllUsersOrderedByNameAsc() {
        // Given
        var user1 = new UserResponseDto( 1L, "Alice", "alice@example.com", Role.MEMBER, true,null,null);
        var user2 = new UserResponseDto(2L, "Bob", "bob@example.com", Role.MEMBER, true,null,null);
        var user3 = new UserResponseDto(3L, "Charlie", "charlie@example.com",  Role.MEMBER, true,null,null);

        when(userRepository.findAllByOrderByNameAsc()).thenReturn(Arrays.asList(user1, user2, user3));

        // When
        List<UserResponseDto> allUsers = underTest.getAllUsers();

        // Then
        verify(userRepository).findAllByOrderByNameAsc();
        assertEquals(3, allUsers.size());
        assertEquals("Alice", allUsers.get(0).name());
        assertEquals("Bob", allUsers.get(1).name());
        assertEquals("Charlie", allUsers.get(2).name());
    }
}