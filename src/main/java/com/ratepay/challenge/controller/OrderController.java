package com.ratepay.challenge.controller;

import com.ratepay.challenge.dto.OrderDto;
import com.ratepay.challenge.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }
}
