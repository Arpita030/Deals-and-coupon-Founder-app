package com.dealsfinder.payment_service.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentMethodNonce;
    private String amount;
    private String userEmail;
    private String dealId;
    private String transactionId;
    private String status;
}
