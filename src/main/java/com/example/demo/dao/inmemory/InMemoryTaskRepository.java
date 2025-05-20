package com.example.demo.dao.inmemory;

import com.example.demo.dao.TaskRepository;
import com.example.demo.model.entity.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Profile("in-memory")
public class InMemoryTaskRepository implements TaskRepository {

    private final Map<UUID, Task> tasks = new LinkedHashMap<>();

    @Override
    public Task save(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAllByUserId(String userId, boolean includeDeleted) {
        return tasks.values().stream()
                    .filter(t -> t.getUserId().equals(userId) && (includeDeleted || !t.isDeleted()))
                    .collect(Collectors.toList());
    }

    @Override
    public List<Task> findPendingByUserId(String userId) {
        return tasks.values().stream()
                    .filter(t -> t.getUserId().equals(userId) && !t.isCompleted() && !t.isDeleted())
                    .collect(Collectors.toList());
    }

    @Override
    public void markAsDeleted(UUID taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.setDeleted(true);
        }
    }

    @Override
    public List<Task> findOverdue() {
        return tasks.values().stream()
                    .filter(t -> !t.isCompleted() && !t.isDeleted() && !t.getTargetDate().isAfter(LocalDateTime.now()))
                    .collect(Collectors.toList());
    }
}
