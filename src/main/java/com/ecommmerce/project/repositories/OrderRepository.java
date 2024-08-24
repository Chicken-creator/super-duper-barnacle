package com.ecommmerce.project.repositories;

import com.ecommmerce.project.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerCustomerId(Long customerId);
}
