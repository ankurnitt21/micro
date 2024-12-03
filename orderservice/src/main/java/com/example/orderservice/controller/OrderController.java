package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // Display product selection
    @GetMapping("/products")
    public String showProducts() {
        return "product"; // Display the products list page
    }

    // Render Order Form page for selected product
    @GetMapping("/place_order")
    public String showOrderForm(@RequestParam String product, Model model) {
        model.addAttribute("product", product);
        return "order_creation"; // Display the order form page
    }

    @PostMapping("/create")
    public String createOrder(@RequestParam Long userId, @RequestParam String product, @RequestParam int quantity, Model model) {
        OrderDTO order = orderService.createOrder(userId, product, quantity);
        if(order.getUserId()==userId){
            return "redirect:/";
        } else {
            model.addAttribute("order", order);  // Add order to model
            return "product";  // Thymeleaf will look for a template named "Order Created.html"
        }
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}

