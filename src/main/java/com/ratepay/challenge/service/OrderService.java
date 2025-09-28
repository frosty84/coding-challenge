package com.ratepay.challenge.service;

import com.ratepay.challenge.dto.OrderDto;
import com.ratepay.challenge.dto.ProductDto;
import com.ratepay.challenge.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getOrders() {
        var orders = orderRepository.findAllWithBuyer();
        return orders.stream().map(order -> {
            var dto = new OrderDto();
            dto.setBuyerId(order.getBuyer().getId().toString());

            var productDtos = order.getProducts().stream().map(product -> {
                var productDto = new ProductDto();
                productDto.setPrice(product.getPrice().toString());
                productDto.setTitle(product.getTitle());
                return productDto;
            }).toList();
            dto.setProducts(productDtos);
            return dto;
        }).toList();
    }
}
