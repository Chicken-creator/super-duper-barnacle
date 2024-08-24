package com.ecommmerce.project.service;

import com.ecommmerce.project.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    void createOrder(Order order);
    String deleteOrder(Long orderId);
    Order updateOrder(Order order, Long orderId);
    List<Order> getOrdersByCustomer(Long customerId);
}
