package com.example.demo.messaging;

public interface Consumer {

    void consumeTaskCreated(String key, String data);
}
