package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Long userId;
    private String product;
    private int quantity;
    private LocalDateTime orderDate;
}
