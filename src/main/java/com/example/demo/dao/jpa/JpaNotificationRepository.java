package com.example.demo.dao.jpa;

import com.example.demo.dao.NotificationRepository;
import com.example.demo.model.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaNotificationRepository implements NotificationRepository {

    private final NotificationJpaRepository jpaRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Notification> findAllByUserId(String userId) {
        return jpaRepo.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notification> findPendingByUserId(String userId) {
        return jpaRepo.findByUserIdAndNotRead(userId);
    }
}
