package com.example.demo.service;

import com.example.demo.dao.NotificationRepository;
import com.example.demo.model.entity.Notification;
import com.example.demo.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceImplTest {

    private NotificationRepository notificationRepository;
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        notificationRepository = mock(NotificationRepository.class);
        notificationService = new NotificationServiceImpl(notificationRepository);
    }

    @Test
    void getAll() {
        Notification n = new Notification();
        n.setUserId("user1");

        when(notificationRepository.findAllByUserId("user1")).thenReturn(List.of(n));

        List<Notification> result = notificationService.getAll("user1");

        assertEquals(1, result.size());
        assertEquals("user1", result.getFirst().getUserId());
    }

    @Test
    void getPending() {
        Notification n = new Notification();
        n.setUserId("user1");
        n.setRead(false);

        when(notificationRepository.findPendingByUserId("user1")).thenReturn(List.of(n));

        List<Notification> result = notificationService.getPending("user1");

        assertEquals(1, result.size());
        assertFalse(result.getFirst().isRead());
    }
}
