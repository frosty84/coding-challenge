package com.ratepay.challenge.controller;

import com.ratepay.challenge.dto.ProductDto;
import com.ratepay.challenge.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/api/products")
    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.findAll();
    }
}
