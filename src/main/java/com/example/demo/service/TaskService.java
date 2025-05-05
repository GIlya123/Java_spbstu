package com.example.demo.service;

import com.example.demo.model.dto.TaskDto;
import com.example.demo.model.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> getAllTasks(String userId);

    List<Task> getPendingTasks(String userId);

    Task createTask(TaskDto task);

    void markTaskAsDeleted(UUID taskId);
}
