package com.khun.testinghelper.repository;

import com.khun.testinghelper.domain.entity.TestEnvironmentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEnvironmentAssignmentRepository extends JpaRepository<TestEnvironmentAssignment, Long> {
}
