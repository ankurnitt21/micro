package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDTO {
    private Long orderId;
    private Long userId;
    private String product;
    private int quantity;
    private LocalDateTime orderDate;

    public OrderDTO(Long orderId, Long userId, String product, int quantity, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public OrderDTO() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return quantity == orderDTO.quantity && Objects.equals(orderId, orderDTO.orderId) && Objects.equals(userId, orderDTO.userId) && Objects.equals(product, orderDTO.product) && Objects.equals(orderDate, orderDTO.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, product, quantity, orderDate);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                '}';
    }
}
