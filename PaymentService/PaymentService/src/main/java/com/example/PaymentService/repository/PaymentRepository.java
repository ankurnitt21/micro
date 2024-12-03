package com.example.PaymentService.repository;

import com.example.PaymentService.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    // You can add custom queries here, e.g., find by userId, or orderId

    // Find payments by userId
    List<PaymentEntity> findByUserId(Long userId);

    // Find payments by orderId
    List<PaymentEntity> findByOrderId(Long orderId);

    // You can also create more complex queries, for example, find payments by status and userId
    List<PaymentEntity> findByUserIdAndStatus(Long userId, String status);

    // Find payments by status (example: to find all successful payments)
    List<PaymentEntity> findByStatus(String status);

    // Find the most recent payment by userId
    PaymentEntity findFirstByUserIdOrderByPaymentDateDesc(Long userId);
}