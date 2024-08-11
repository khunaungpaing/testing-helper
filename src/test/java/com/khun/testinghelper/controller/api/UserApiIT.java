package com.khun.testinghelper.controller.api;

import com.khun.testinghelper.TestcontainersConfiguration;
import com.khun.testinghelper.domain.dto.UserRequestDto;
import com.khun.testinghelper.domain.dto.UserResponseDto;
import com.khun.testinghelper.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
public class UserApiIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Test
    void testGetAllUsers() {
        // Given
        UserRequestDto user1 = new UserRequestDto("Alice", "alice@example.com", "password1");
        UserRequestDto user2 = new UserRequestDto("Bob", "bob@example.com", "password2");

        userService.insertUser(user1);
        userService.insertUser(user2);
        // When
        ResponseEntity<UserResponseDto[]> response = restTemplate.getForEntity("/api/v1/users", UserResponseDto[].class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    void testCreateUser() {
        // Given
        UserRequestDto newUser = new UserRequestDto("Charlie", "charlie@example.com", "password3");

        // When
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/v1/users", newUser, Void.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertTrue(userService.isUserExistsByEmail("charlie@example.com"));
    }
}