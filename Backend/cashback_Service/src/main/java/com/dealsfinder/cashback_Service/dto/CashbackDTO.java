package com.dealsfinder.cashback_Service.dto;

import lombok.Data;

@Data
public class CashbackDTO {
    private String userEmail;
    private String dealId;
    private Double cashbackAmount;
}
