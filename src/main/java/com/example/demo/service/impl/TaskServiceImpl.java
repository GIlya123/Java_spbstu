package com.example.demo.service.impl;

import com.example.demo.dao.TaskRepository;
import com.example.demo.model.dto.TaskDto;
import com.example.demo.model.entity.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks(String userId) {
        return taskRepository.findAllByUserId(userId, false);
    }

    @Override
    public List<Task> getPendingTasks(String userId) {
        return taskRepository.findPendingByUserId(userId);
    }

    @Override
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
    public void markTaskAsDeleted(UUID taskId) {
        taskRepository.markAsDeleted(taskId);
    }
}
