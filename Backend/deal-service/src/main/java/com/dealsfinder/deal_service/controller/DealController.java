package com.dealsfinder.deal_service.controller;


import com.dealsfinder.deal_service.dto.DealDTO;
import com.dealsfinder.deal_service.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/deals")
public class DealController {

    @Autowired
    private final DealService service;

    public DealController(DealService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DealDTO> create(@RequestBody DealDTO dealDTO) {
        return ResponseEntity.ok(service.createDeal(dealDTO));
    }

    @GetMapping
    public ResponseEntity<List<DealDTO>> getAll() {
        return ResponseEntity.ok(service.getAllDeals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDealById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealDTO> update(@PathVariable Long id, @RequestBody DealDTO dealDTO) {
        return ResponseEntity.ok(service.updateDeal(id, dealDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteDeal(id);
        return ResponseEntity.ok("Deal deleted successfully!");
    }
}