package com.dealsfinder.payment_service.dto;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashbackMessage {
    private String userEmail;
    private String dealId;
    private double cashbackAmount;
}
