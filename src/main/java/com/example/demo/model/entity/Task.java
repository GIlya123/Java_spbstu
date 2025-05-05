package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Task {

    private UUID id;
    private String userId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime targetDate;
    private boolean isCompleted;
    private boolean isDeleted;
}
