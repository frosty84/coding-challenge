package com.ratepay.challenge.service;

import com.ratepay.challenge.dto.ProductDto;
import com.ratepay.challenge.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAll(String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        return productRepository.findAll(sort).stream().map(product -> new ProductDto(product.getTitle(), product.getPrice().toString())).toList();
    }
}
