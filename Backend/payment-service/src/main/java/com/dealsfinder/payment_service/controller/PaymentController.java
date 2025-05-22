package com.dealsfinder.payment_service.controller;


import com.dealsfinder.payment_service.dto.PaymentRequestDto;
import com.dealsfinder.payment_service.dto.PaymentResponseDto;
import com.dealsfinder.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> makePayment(@RequestBody PaymentRequestDto requestDto) {
        // Get authenticated user's email from JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // email/username from JWT

        // Inject user's email into the payment request
        requestDto.setUserEmail(userEmail);

        // Process payment
        PaymentResponseDto response = paymentService.processPayment(requestDto);
        return ResponseEntity.ok(response);
    }
}
