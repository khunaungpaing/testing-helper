package com.khun.testinghelper.repository;

import com.khun.testinghelper.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
