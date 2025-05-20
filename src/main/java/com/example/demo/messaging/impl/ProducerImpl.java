package com.example.demo.messaging.impl;

import com.example.demo.messaging.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerImpl implements Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Отправляет сообщение в брокер
     *
     * @param topic топик
     * @param key ключ сообщения
     * @param data тело сообщения
     */
    @Async
    @Override
    public void send(String topic, String key, String data) {
        kafkaTemplate.send(topic, key, data);
        log.info("Message was sent to topic: {}, key: {}, data: {}", topic, key, data);
    }
}
