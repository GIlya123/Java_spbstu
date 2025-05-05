package com.example.demo.service.impl;

import com.example.demo.dao.NotificationRepository;
import com.example.demo.model.entity.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> getAll(String userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    @Override
    public List<Notification> getPending(String userId) {
        return notificationRepository.findPendingByUserId(userId);
    }
}
