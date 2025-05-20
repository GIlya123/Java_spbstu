package com.example.demo.service.impl;

import com.example.demo.dao.TaskRepository;
import com.example.demo.model.dto.TaskDto;
import com.example.demo.model.entity.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "tasks", key = "#userId")
    public List<Task> getAllTasks(String userId) {
        log.info("getAllTasks( {} )", userId);
        return taskRepository.findAllByUserId(userId, true);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "tasks-pending", key = "#userId")
    public List<Task> getPendingTasks(String userId) {
        log.info("getPendingTasks( {} )", userId);
        return taskRepository.findPendingByUserId(userId);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"tasks", "tasks-pending"}, allEntries = true)
    public Task createTask(TaskDto dto) {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setUserId(dto.getUserId());
        task.setTitle(dto.getTitle());
        task.setTargetDate(dto.getTargetDate());
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);
        task.setDeleted(false);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"tasks", "tasks-pending"}, allEntries = true)
    public void markTaskAsDeleted(UUID taskId) {
        taskRepository.markAsDeleted(taskId);
    }
}
