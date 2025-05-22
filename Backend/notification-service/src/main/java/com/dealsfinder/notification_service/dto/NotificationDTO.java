package com.dealsfinder.notification_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private String toEmail;
    private String subject;
    private String message;
}
