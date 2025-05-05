package com.example.demo.dao.impl;

import com.example.demo.dao.NotificationRepository;
import com.example.demo.model.Notification;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("in-memory")
public class InMemoryNotificationRepository implements NotificationRepository {

    private final List<Notification> notifications = new ArrayList<>();

    @Override
    public List<Notification> findAllByUserId(String userId) {
        return notifications.stream()
                            .filter(n -> n.getUserId().equals(userId))
                            .collect(Collectors.toList());
    }

    @Override
    public List<Notification> findPendingByUserId(String userId) {
        return notifications.stream()
                            .filter(n -> n.getUserId().equals(userId) && !n.isRead())
                            .collect(Collectors.toList());
    }
}
