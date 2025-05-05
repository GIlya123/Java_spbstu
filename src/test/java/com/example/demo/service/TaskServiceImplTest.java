package com.example.demo.service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.model.dto.TaskDto;
import com.example.demo.model.entity.Task;
import com.example.demo.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskServiceImplTest {

    private TaskRepository taskRepository;
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskServiceImpl(taskRepository);
    }

    @Test
    void createTask() {
        TaskDto dto = new TaskDto();
        dto.setUserId("user1");
        dto.setTitle("Test task");
        dto.setTargetDate(LocalDateTime.now().plusDays(1));

        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArguments()[0]);

        Task result = taskService.createTask(dto);

        assertNotNull(result.getId());
        assertFalse(result.isCompleted());
        assertFalse(result.isDeleted());
        assertEquals("user1", result.getUserId());
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void getAllTasks() {
        Task task = new Task();
        task.setUserId("user1");

        when(taskRepository.findAllByUserId("user1", false)).thenReturn(List.of(task));

        List<Task> result = taskService.getAllTasks("user1");

        assertEquals(1, result.size());
        assertEquals("user1", result.getFirst().getUserId());
    }

    @Test
    void markTaskAsDeleted() {
        UUID taskId = UUID.randomUUID();

        taskService.markTaskAsDeleted(taskId);

        verify(taskRepository).markAsDeleted(taskId);
    }
}
