package com.example.PaymentService.service;

import com.example.PaymentService.dto.PaymentDTO;
import com.example.PaymentService.entity.PaymentEntity;
import com.example.PaymentService.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Method to create a payment
    public PaymentDTO createPayment(Long userId, Long orderId, Double amount) {
        // In a real-world scenario, you would process the payment here (e.g., interact with a payment gateway)

        // For this example, we'll assume the payment is always successful
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .userId(userId)
                .orderId(orderId)
                .amount(amount)
                .status("SUCCESS")  // Assuming the payment is successful
                .paymentDate(LocalDateTime.now())
                .build();

        // Save the payment entity to the database
        PaymentEntity savedPayment = paymentRepository.save(paymentEntity);

        // Convert to DTO and return
        return new PaymentDTO(
                savedPayment.getUserId(),
                savedPayment.getOrderId(),
                savedPayment.getAmount(),
                savedPayment.getStatus(),
                savedPayment.getPaymentDate()
        );
    }

    // Method to find payments by userId
    public List<PaymentDTO> getPaymentsByUserId(Long userId) {
        List<PaymentEntity> payments = paymentRepository.findByUserId(userId);
        return payments.stream()
                .map(payment -> new PaymentDTO(
                        payment.getUserId(),
                        payment.getOrderId(),
                        payment.getAmount(),
                        payment.getStatus(),
                        payment.getPaymentDate()
                ))
                .collect(Collectors.toList());
    }

    // Method to find payments by orderId
    public List<PaymentDTO> getPaymentsByOrderId(Long orderId) {
        List<PaymentEntity> payments = paymentRepository.findByOrderId(orderId);
        return payments.stream()
                .map(payment -> new PaymentDTO(
                        payment.getUserId(),
                        payment.getOrderId(),
                        payment.getAmount(),
                        payment.getStatus(),
                        payment.getPaymentDate()
                ))
                .collect(Collectors.toList());
    }

    // Method to find payments by userId and status
    public List<PaymentDTO> getPaymentsByUserIdAndStatus(Long userId, String status) {
        List<PaymentEntity> payments = paymentRepository.findByUserIdAndStatus(userId, status);
        return payments.stream()
                .map(payment -> new PaymentDTO(
                        payment.getUserId(),
                        payment.getOrderId(),
                        payment.getAmount(),
                        payment.getStatus(),
                        payment.getPaymentDate()
                ))
                .collect(Collectors.toList());
    }

    // Method to find payments by status (e.g., to find all successful payments)
    public List<PaymentDTO> getPaymentsByStatus(String status) {
        List<PaymentEntity> payments = paymentRepository.findByStatus(status);
        return payments.stream()
                .map(payment -> new PaymentDTO(
                        payment.getUserId(),
                        payment.getOrderId(),
                        payment.getAmount(),
                        payment.getStatus(),
                        payment.getPaymentDate()
                ))
                .collect(Collectors.toList());
    }

    // Method to get the most recent payment by userId
    public PaymentDTO getMostRecentPaymentByUserId(Long userId) {
        PaymentEntity paymentEntity = paymentRepository.findFirstByUserIdOrderByPaymentDateDesc(userId);
        if (paymentEntity != null) {
            return new PaymentDTO(
                    paymentEntity.getUserId(),
                    paymentEntity.getOrderId(),
                    paymentEntity.getAmount(),
                    paymentEntity.getStatus(),
                    paymentEntity.getPaymentDate()
            );
        }
        return null; // Return null if no payments found for the user
    }
}
