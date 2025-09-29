package com.ratepay.challenge.repository;

import com.ratepay.challenge.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
    @Override
    List<Product> findAll();
}
