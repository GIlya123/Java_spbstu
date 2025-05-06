package com.example.demo.job;

import com.example.demo.dao.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskOverdueJob {

    private final TaskProcessor taskProcessor;
    private final TaskRepository taskRepository;

    /**
     * Каждую минуту проверяет задачи на срок
     */
    @Scheduled(fixedRate = 60_000L)
    public void checkTasks() {
        taskRepository.findOverdue().forEach(taskProcessor::handleOverdueTask);
    }
}
