package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    private UUID id;
    private String userId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime targetDate;
    private boolean isCompleted;
    private boolean isDeleted;
}
