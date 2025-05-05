package com.example.demo.controller;

import com.example.demo.model.entity.Notification;
import com.example.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAll(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(notificationService.getAll(userId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Notification>> getPending(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(notificationService.getPending(userId));
    }
}
