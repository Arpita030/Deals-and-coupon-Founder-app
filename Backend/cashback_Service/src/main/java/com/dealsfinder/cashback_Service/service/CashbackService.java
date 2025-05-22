package com.dealsfinder.cashback_Service.service;

import com.dealsfinder.cashback_Service.dto.CashbackDTO;
import com.dealsfinder.cashback_Service.model.Cashback;
import com.dealsfinder.cashback_Service.model.CashbackSummary;
import com.dealsfinder.cashback_Service.repository.CashbackRepository;
import com.dealsfinder.cashback_Service.repository.CashbackSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CashbackService {

    @Autowired
    private CashbackRepository cashbackRepository;

    @Autowired
    private CashbackSummaryRepository summaryRepository;

    public CashbackDTO saveCashback(CashbackDTO dto) {
        Cashback cashback = Cashback.builder()
                .dealId(dto.getDealId())
                .cashbackAmount(dto.getCashbackAmount())
                .timestamp(LocalDateTime.now())
                .build();
        cashbackRepository.save(cashback);

        CashbackSummary summary = summaryRepository.findById(dto.getUserEmail())
                .orElse(CashbackSummary.builder()
                        .userEmail(dto.getUserEmail())
                        .totalCashback(0.0)
                        .build());

        summary.setTotalCashback(summary.getTotalCashback() + dto.getCashbackAmount());
        summaryRepository.save(summary);

        return dto;
    }
}
