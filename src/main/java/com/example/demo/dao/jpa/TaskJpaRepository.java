package com.example.demo.dao.jpa;

import com.example.demo.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<Task, UUID> {

    List<Task> findByUserId(String userId);

    @Query("select t from Task t where t.userId = ?1 and t.isCompleted = true and t.isDeleted = false")
    List<Task> findByUserIdAndCompletedAndNotDeleted(String userId);

    @Query("select t from Task t where t.userId = ?1 and t.isCompleted = false and t.isDeleted = false")
    List<Task> findByUserIdAndNotCompletedAndNotDeleted(String userId);
}
