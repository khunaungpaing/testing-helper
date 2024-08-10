package com.khun.testinghelper.repository;

import com.khun.testinghelper.domain.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
}
