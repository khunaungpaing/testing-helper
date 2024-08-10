package com.khun.testinghelper.repository;

import com.khun.testinghelper.domain.dto.UserResponseDto;
import com.khun.testinghelper.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select count(u) > 0 from User u where u.email = :email")
    boolean existsByEmail(@Param("email") String email);
    Optional<UserResponseDto> findByEmail(String email);
}
