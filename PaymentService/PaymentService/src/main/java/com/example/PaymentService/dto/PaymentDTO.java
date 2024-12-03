package com.example.PaymentService.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {

    private Long userId;
    private Long orderId; // Associated order ID
    private Double amount;
    private String status;
    private LocalDateTime paymentDate;

    // Constructor for ease of use
    public PaymentDTO(Long userId, Long orderId, Double amount, String status, LocalDateTime paymentDate) {
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
    }
}