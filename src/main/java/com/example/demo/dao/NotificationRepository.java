package com.example.demo.dao;

import com.example.demo.model.entity.Notification;

import java.util.List;

public interface NotificationRepository {

    List<Notification> findAllByUserId(String userId);

    List<Notification> findPendingByUserId(String userId);
}
