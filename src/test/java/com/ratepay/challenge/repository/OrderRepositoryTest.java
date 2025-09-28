package com.ratepay.challenge.repository;

import com.ratepay.challenge.entity.Buyer;
import com.ratepay.challenge.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("findAll returns all orders when repository is not empty")
    @Test
    void findAllReturnsAllOrdersWhenRepositoryIsNotEmpty() {
        var buyer1 = new Buyer();
        buyer1.setId(UUID.randomUUID());
        buyer1.setName("John Doe");

        var buyer2 = new Buyer();
        buyer2.setId(UUID.randomUUID());
        buyer2.setName("Jane Doe");

        Order order1 = new Order();
        order1.setId(UUID.randomUUID());
        order1.setBuyer(buyer1);

        Order order2 = new Order();
        order2.setId(UUID.randomUUID());
        order2.setBuyer(buyer2);
        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = orderRepository.findAllWithBuyer();

        assertThat(orders).hasSize(2).contains(order1, order2);
    }
}
