package com.khun.testinghelper.repository;

import com.khun.testinghelper.domain.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
}
