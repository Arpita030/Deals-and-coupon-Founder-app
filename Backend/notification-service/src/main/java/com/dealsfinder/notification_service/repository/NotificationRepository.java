package com.dealsfinder.notification_service.repository;


import com.dealsfinder.notification_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
