package com.dealsfinder.cashback_Service.controller;

import com.dealsfinder.cashback_Service.dto.CashbackDTO;
import com.dealsfinder.cashback_Service.exception.EmailMismatchException;
import com.dealsfinder.cashback_Service.service.CashbackService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@RestController
@RequestMapping("/api/cashback")
public class CashbackController {

    private final String SECRET_KEY = "abcdefghijklmnopqrstuvwxyz012345";

    @Autowired
    private CashbackService cashbackService;

    @PostMapping
    public ResponseEntity<CashbackDTO> createCashback(@RequestBody CashbackDTO dto,
                                                      HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new EmailMismatchException("Missing or invalid Authorization header");
        }

        String token = header.substring(7);
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String emailFromToken = claims.getSubject(); // or claims.get("email", String.class);

        if (!emailFromToken.equals(dto.getUserEmail())) {
            throw new EmailMismatchException("Email mismatch: You are not authorized to submit cashback for this user.");
        }

        CashbackDTO saved = cashbackService.saveCashback(dto);
        return ResponseEntity.ok(saved);
    }
}
