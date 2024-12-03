package com.example.PaymentService.controller;

import com.example.PaymentService.dto.PaymentDTO;
import com.example.PaymentService.entity.PaymentEntity;
import com.example.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Endpoint to create a new payment
    @PostMapping("/create")
    public ResponseEntity<PaymentDTO> createPayment(
            @RequestParam Long userId,
            @RequestParam Long orderId,
            @RequestParam Double amount) {

        try {
            // Create a new payment by calling the service
            PaymentDTO paymentDTO = paymentService.createPayment(userId, orderId, amount);

            // Return the created payment with a success status
            return new ResponseEntity<>(paymentDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            // Return an error response if something goes wrong
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to find all payments for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserId(@PathVariable Long userId) {
        try {
            List<PaymentDTO> payments = paymentService.getPaymentsByUserId(userId);
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to find all payments for a specific order
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByOrderId(@PathVariable Long orderId) {
        try {
            List<PaymentDTO> payments = paymentService.getPaymentsByOrderId(orderId);
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to find all payments for a specific user and status
    @GetMapping("/user/{userId}/status/{status}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserIdAndStatus(@PathVariable Long userId, @PathVariable String status) {
        try {
            List<PaymentDTO> payments = paymentService.getPaymentsByUserIdAndStatus(userId, status);
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to find payments by status (e.g., to get all successful payments)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByStatus(@PathVariable String status) {
        try {
            List<PaymentDTO> payments = paymentService.getPaymentsByStatus(status);
            if (payments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to find the most recent payment by userId
    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<PaymentDTO> getMostRecentPaymentByUserId(@PathVariable Long userId) {
        try {
            PaymentDTO paymentDTO = paymentService.getMostRecentPaymentByUserId(userId);
            if (paymentDTO == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
