package com.khun.testinghelper.repository;

import com.khun.testinghelper.domain.entity.ProjectVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectVersionRepository extends JpaRepository<ProjectVersion, Long> {
}
