package com.dealsfinder.notification_service.controller;


import com.dealsfinder.notification_service.dto.NotificationDTO;
import com.dealsfinder.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @PostMapping
    public ResponseEntity<String> send(@RequestBody NotificationDTO dto) {
        service.sendNotification(dto);
        return ResponseEntity.ok("Notification sent successfully!");
    }
}
