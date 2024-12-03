package com.example.PaymentService.entity;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // ID of the user making the payment

    private Long orderId; // ID of the associated order

    private Double amount; // The amount paid

    private String status; // Payment status (e.g., "SUCCESS", "FAILED")

    private LocalDateTime paymentDate; // Payment timestamp

    @Builder
    public PaymentEntity(Long userId, Long orderId, Double amount, String status, LocalDateTime paymentDate) {
        this.userId = userId;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
    }
}