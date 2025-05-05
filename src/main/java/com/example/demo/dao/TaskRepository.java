package com.example.demo.dao;


import com.example.demo.model.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskRepository {

    Task save(Task task);

    List<Task> findAllByUserId(String userId, boolean includeDeleted);

    List<Task> findPendingByUserId(String userId);

    void markAsDeleted(UUID taskId);
}
