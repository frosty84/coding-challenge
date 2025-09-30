package com.ratepay.challenge.repository;

import com.ratepay.challenge.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {
    @Query("SELECT o.id FROM Order o")
    Page<UUID> findOrderIds(Pageable pageable);

    @Query("SELECT DISTINCT o FROM Order o " +
            "JOIN FETCH o.buyer " +
            "LEFT JOIN FETCH o.products " +
            "WHERE o.id IN :ids")
    List<Order> findAllWithBuyerAndProductsByIds(@Param("ids") List<UUID> ids);

    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.buyer LEFT JOIN FETCH o.products WHERE o.id = :id")
    Order findByIdWithBuyerAndProducts(UUID id);
}
