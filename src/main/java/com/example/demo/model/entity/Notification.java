package com.example.demo.model.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Notification {

    private UUID id;
    private String userId;
    private String message;
    private boolean isRead;
}
