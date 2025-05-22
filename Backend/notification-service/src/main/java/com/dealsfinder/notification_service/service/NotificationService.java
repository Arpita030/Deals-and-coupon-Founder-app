package com.dealsfinder.notification_service.service;


import com.dealsfinder.notification_service.dto.NotificationDTO;
import com.dealsfinder.notification_service.model.Notification;
import com.dealsfinder.notification_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public void sendNotification(NotificationDTO dto) {
        // Simulate sending email
        System.out.println("Sending email to: " + dto.getToEmail());
        System.out.println("Subject: " + dto.getSubject());
        System.out.println("Message: " + dto.getMessage());

        // Save log
        Notification notification = Notification.builder()
                .toEmail(dto.getToEmail())
                .subject(dto.getSubject())
                .message(dto.getMessage())
                .sentAt(LocalDateTime.now())
                .build();

        repository.save(notification);
    }
}
