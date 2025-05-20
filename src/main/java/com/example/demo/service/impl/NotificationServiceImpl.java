package com.example.demo.service.impl;

import com.example.demo.dao.NotificationRepository;
import com.example.demo.model.entity.Notification;
import com.example.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * @return все оповещения для пользователя
     */
    @Override
    @Transactional(readOnly = true)
    public List<Notification> getAll(String userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    /**
     * @return только непрочитанные оповещения
     */
    @Override
    @Transactional(readOnly = true)
    public List<Notification> getPending(String userId) {
        return notificationRepository.findPendingByUserId(userId);
    }
}
