package com.dealsfinder.deal_service.service;

import com.dealsfinder.deal_service.dto.DealDTO;
import com.dealsfinder.deal_service.model.Deal;
import com.dealsfinder.deal_service.repository.DealRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DealService {

    @Autowired
    private final DealRepository repository;

    public DealService(DealRepository repository) {
        this.repository = repository;
    }

    public DealDTO createDeal(DealDTO dto) {
        Deal deal = dtoToEntity(dto);
        return entityToDto(repository.save(deal));
    }

    public List<DealDTO> getAllDeals() {
        return repository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public DealDTO getDealById(Long id) {
        Optional<Deal> deal = repository.findById(id);
        return deal.map(this::entityToDto).orElse(null);
    }

    public DealDTO updateDeal(Long id, DealDTO dto) {
        Optional<Deal> optionalDeal = repository.findById(id);
        if (optionalDeal.isPresent()) {
            Deal deal = optionalDeal.get();
            deal.setTitle(dto.getTitle());
            deal.setDescription(dto.getDescription());
            deal.setDiscountPercentage(dto.getDiscountPercentage());
            deal.setStartDate(dto.getStartDate());
            deal.setEndDate(dto.getEndDate());
            deal.setActive(dto.isActive());
            return entityToDto(repository.save(deal));
        }
        return null;
    }

    public void deleteDeal(Long id) {
        repository.deleteById(id);
    }

    private Deal dtoToEntity(DealDTO dto) {
        return Deal.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .discountPercentage(dto.getDiscountPercentage())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .active(dto.isActive())
                .build();
    }

    private DealDTO entityToDto(Deal deal) {
        return DealDTO.builder()
                .id(deal.getId())
                .title(deal.getTitle())
                .description(deal.getDescription())
                .discountPercentage(deal.getDiscountPercentage())
                .startDate(deal.getStartDate())
                .endDate(deal.getEndDate())
                .active(deal.isActive())
                .build();
    }
}
