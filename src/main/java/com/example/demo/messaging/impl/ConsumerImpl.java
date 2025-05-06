package com.example.demo.messaging.impl;

import com.example.demo.configuration.KafkaConfig;
import com.example.demo.dao.NotificationRepository;
import com.example.demo.messaging.Consumer;
import com.example.demo.model.entity.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerImpl implements Consumer {

    private final NotificationRepository notificationRepository;

    /**
     * Слушает топик и создает оповещение в БД
     *
     * @param key  userId
     * @param data task
     */
    @Override
    @KafkaListener(topics = KafkaConfig.TASK_CREATED_TOPIC, groupId = "notification-service", containerFactory = "kafkaListenerContainerFactory")
    public void consumeTaskCreated(@Header(KafkaHeaders.RECEIVED_KEY) String key, @Payload String data) {
        log.info("Message received in topic: {}, key: {}", KafkaConfig.TASK_CREATED_TOPIC, key);
        Notification notification = new Notification();
        notification.setId(UUID.randomUUID());
        notification.setUserId(key);
        notification.setMessage("New task created: " + data);
        notification.setRead(false);
        notificationRepository.save(notification);
    }
}
