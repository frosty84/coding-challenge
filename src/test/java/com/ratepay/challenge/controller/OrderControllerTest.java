package com.ratepay.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratepay.challenge.service.OrderService;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Test
    @Transactional
    void shouldReturnPagedListOfOrders() throws Exception {
        PageRequest pageable = PageRequest.of(0, 100);
        Page<?> expectedPage = orderService.getOrders(pageable);
        String expectedJson =  new ObjectMapper().writeValueAsString(expectedPage);

        mockMvc.perform(get("/api/orders")
                        .param("page", "0")
                        .param("size", "100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content.length()").value(expectedPage.getNumberOfElements()))
                .andExpect(content().json(expectedJson));
    }

    @Test
    @Transactional
    void shouldReturnSingleOrder() throws Exception {
        PageRequest pageable = PageRequest.of(0, 100);
        var order = orderService.getOrders(pageable)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No orders found in the database"));;
        var ordersJson = new ObjectMapper().writeValueAsString(order);

        mockMvc.perform(get("/api/order/{id}", order.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(ordersJson));
    }

}