package com.example.demo.job;

import com.example.demo.dao.TaskRepository;
import com.example.demo.model.entity.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskProcessor {

    private final TaskRepository taskRepository;

    /**
     * Удаляет просроченные задачи
     *
     * @param task overdue task
     */
    @Async
    public void handleOverdueTask(Task task) {
        log.warn("Task is OVERDUE ({}): {}", task.getTargetDate(), task);
        taskRepository.markAsDeleted(task.getId());
    }
}
