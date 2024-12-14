package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO createOrder(Long userId, String product, int quantity) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(userId);
        orderEntity.setProduct(product);
        orderEntity.setQuantity(quantity);
        orderEntity.setOrderDate(LocalDateTime.now());

        System.out.println(userId);
        System.out.println(product);
        System.out.println(quantity);

        orderEntity = orderRepository.save(orderEntity);

        return new OrderDTO(orderEntity.getOrderId(), orderEntity.getUserId(), orderEntity.getProduct(),
                orderEntity.getQuantity(), orderEntity.getOrderDate());
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<OrderEntity> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> new OrderDTO(order.getOrderId(), order.getUserId(), order.getProduct(),
                        order.getQuantity(), order.getOrderDate()))
                .collect(Collectors.toList());
    }
}
