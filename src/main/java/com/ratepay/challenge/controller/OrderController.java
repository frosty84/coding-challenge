package com.ratepay.challenge.controller;

import com.ratepay.challenge.dto.OrderDto;
import com.ratepay.challenge.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/api/orders")
    @GetMapping
    public Page<OrderDto> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return orderService.getOrders(PageRequest.of(page, size));
    }

    @RequestMapping("/api/order/{id}")
    @GetMapping
    public OrderDto getOrderById(@PathVariable UUID id) {
        return orderService.getOrder(id);
    }
}
