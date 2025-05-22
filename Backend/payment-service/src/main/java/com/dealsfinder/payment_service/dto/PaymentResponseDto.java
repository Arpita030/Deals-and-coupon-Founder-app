package com.dealsfinder.payment_service.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDto {
    private String transactionId;
    private String status;
    private String message;
}
