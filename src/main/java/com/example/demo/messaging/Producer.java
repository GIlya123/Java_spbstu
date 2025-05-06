package com.example.demo.messaging;

public interface Producer {

    void send(String topic, String key, String data);
}
