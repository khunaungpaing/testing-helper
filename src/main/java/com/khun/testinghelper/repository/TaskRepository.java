package com.khun.testinghelper.repository;

import com.khun.testinghelper.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
