package com.example.demo.service;

import com.example.demo.dao.NotificationRepository;
import com.example.demo.model.entity.Notification;
import com.example.demo.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void getAll() {
        Notification n1 = new Notification();
        n1.setUserId("user123");

        when(notificationRepository.findAllByUserId("user123")).thenReturn(List.of(n1));

        List<Notification> result = notificationService.getAll("user123");

        assertEquals(1, result.size());
        assertEquals("user123", result.getFirst().getUserId());
        verify(notificationRepository).findAllByUserId("user123");
    }

    @Test
    void getPending() {
        Notification n1 = new Notification();
        n1.setUserId("user123");
        n1.setRead(false);

        when(notificationRepository.findPendingByUserId("user123")).thenReturn(List.of(n1));

        List<Notification> result = notificationService.getPending("user123");

        assertEquals(1, result.size());
        assertFalse(result.getFirst().isRead());
        verify(notificationRepository).findPendingByUserId("user123");
    }
}
