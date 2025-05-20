package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    private UUID id;
    private String userId;
    private String message;
    private boolean isRead;
}
