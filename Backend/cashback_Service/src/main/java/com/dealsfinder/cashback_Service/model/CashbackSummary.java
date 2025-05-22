package com.dealsfinder.cashback_Service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashbackSummary {

    @Id
    private String userEmail;

    private Double totalCashback;
}
