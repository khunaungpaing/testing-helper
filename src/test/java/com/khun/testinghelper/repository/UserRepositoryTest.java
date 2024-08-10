package com.khun.testinghelper.repository;

import com.khun.testinghelper.TestcontainersConfiguration;
import com.khun.testinghelper.domain.entity.User;
import com.khun.testinghelper.domain.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    @Test
    void existsByEmail() {
        // Given
        User user = new User();
        user.setName("test");
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setActive(true);
        user.setRole(Role.PROJECT_MANAGER);
        underTest.save(user);
        // Then
        assertTrue(underTest.existsByEmail("test@test.com"));
        assertFalse(underTest.existsByEmail("nonexist@test.com"));


    }

    @Test
    void findByEmail() {
        // Given
        User user = new User();
        user.setName("test");
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setActive(true);
        user.setRole(Role.PROJECT_MANAGER);
        underTest.save(user);

        // When
        var returnValue = underTest.findByEmail("test@test.com");
        // Then
        assertThat(returnValue).isPresent().hasValueSatisfying(v->{
            assertEquals(user.getEmail(),v.email());
            assertEquals(user.getName(),v.name());
            assertEquals(user.getRole(), v.role());
            assertEquals(user.getCreatedDate(), v.createdDate());
            assertEquals(user.isActive(), v.active());
        });
        assertFalse(underTest.findByEmail("nonexist@test.com").isPresent());
    }
}