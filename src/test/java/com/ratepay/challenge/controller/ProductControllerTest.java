package com.ratepay.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratepay.challenge.dto.ProductDto;
import com.ratepay.challenge.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllProducts() throws Exception {
        var expectedProducts = new ProductDto[]{
                new ProductDto("Book", "1.20"),
                new ProductDto("Cup", "3.50"),
                new ProductDto("Shelf", "70.00"),
                new ProductDto("Guitar", "250.00"),
                new ProductDto("Ball", "29.00"),
                new ProductDto("Shoes", "120.00"),
                new ProductDto("Desk", "499.00")
        };
        var expectedJson = new ObjectMapper().writeValueAsString(Arrays.asList(expectedProducts));
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.length()").value(7))
                .andExpect(content().json(expectedJson));
    }
}
