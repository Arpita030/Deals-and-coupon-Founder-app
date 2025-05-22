package com.dealsfinder.deal_service.repository;


import com.dealsfinder.deal_service.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
