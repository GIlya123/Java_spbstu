package com.example.demo.dao.jpa;

import com.example.demo.dao.TaskRepository;
import com.example.demo.model.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaTaskRepository implements TaskRepository {

    private final TaskJpaRepository jpaRepository;

    @Override
    @Transactional
    public Task save(Task task) {
        return jpaRepository.saveAndFlush(task);
    }

    @Override
    public List<Task> findAllByUserId(String userId, boolean includeDeleted) {
        return includeDeleted
                ? jpaRepository.findByUserId(userId)
                : jpaRepository.findByUserIdAndNotDeleted(userId);
    }

    @Override
    public List<Task> findPendingByUserId(String userId) {
        return jpaRepository.findByUserIdAndNotCompletedAndNotDeleted(userId);
    }

    @Override
    @Transactional
    public void markAsDeleted(UUID taskId) {
        jpaRepository.findById(taskId).ifPresent(task -> {
            task.setDeleted(true);
            jpaRepository.save(task);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findOverdue() {
        return jpaRepository.findOverdue();
    }
}
