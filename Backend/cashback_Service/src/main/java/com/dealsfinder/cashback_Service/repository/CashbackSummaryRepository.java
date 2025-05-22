package com.dealsfinder.cashback_Service.repository;

import com.dealsfinder.cashback_Service.model.CashbackSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashbackSummaryRepository extends JpaRepository<CashbackSummary, String> {}
