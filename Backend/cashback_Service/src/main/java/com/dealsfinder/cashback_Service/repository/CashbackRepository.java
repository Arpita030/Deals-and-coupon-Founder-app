package com.dealsfinder.cashback_Service.repository;

import com.dealsfinder.cashback_Service.model.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashbackRepository extends JpaRepository<Cashback, Long> {}
