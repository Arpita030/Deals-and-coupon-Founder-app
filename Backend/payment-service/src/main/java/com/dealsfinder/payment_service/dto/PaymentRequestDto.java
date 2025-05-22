package com.dealsfinder.payment_service.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDto {
    private String paymentMethodNonce;
    private String amount;
    private String userEmail;
    private String dealId;

}
