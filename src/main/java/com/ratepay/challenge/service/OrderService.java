package com.ratepay.challenge.service;

import com.ratepay.challenge.dto.OrderDto;
import com.ratepay.challenge.dto.ProductDto;
import com.ratepay.challenge.entity.Order;
import com.ratepay.challenge.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<OrderDto> getOrders(Pageable pageable) {
        var orderIdsPage = orderRepository.findOrderIds(pageable);

        if (orderIdsPage.isEmpty()) {
            return Page.empty(pageable);
        }

        var orders = orderRepository.findAllWithBuyerAndProductsByIds(orderIdsPage.getContent());

        List<OrderDto> dtos = orders.stream()
                .map(OrderService::mapOrderToDto)
                .toList();

        return new PageImpl<>(dtos, pageable, orderIdsPage.getTotalElements());
    }

    public OrderDto getOrder(UUID orderId) {
        var order = orderRepository.findByIdWithBuyerAndProducts(orderId);
        return mapOrderToDto(order);
    }

    public static OrderDto mapOrderToDto(Order order) {
        var dto = new OrderDto();
        dto.setBuyerId(order.getBuyer().getId().toString());
        dto.setId(order.getId());

        var productDtos = order.getProducts().stream().map(product -> {
            var productDto = new ProductDto();
            productDto.setPrice(product.getPrice().toString());
            productDto.setTitle(product.getTitle());
            return productDto;
        }).toList();
        dto.setProducts(productDtos);
        return dto;
    }
}
