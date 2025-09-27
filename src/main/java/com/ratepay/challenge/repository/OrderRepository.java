package com.ratepay.challenge.repository;

import com.ratepay.challenge.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
    @Override
    List<Order> findAll();
}
