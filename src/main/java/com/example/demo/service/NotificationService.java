package com.example.demo.service;

import com.example.demo.model.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getAll(String userId);

    List<Notification> getPending(String userId);
}
