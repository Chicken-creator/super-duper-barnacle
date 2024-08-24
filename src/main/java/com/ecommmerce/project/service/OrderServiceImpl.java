package com.ecommmerce.project.service;

import com.ecommmerce.project.model.Customer;
import com.ecommmerce.project.model.Order;
import com.ecommmerce.project.repositories.CustomerRepository;
import com.ecommmerce.project.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(final OrderRepository orderRepository, final CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void createOrder(final Order order) {
        if (order.getCustomer() != null) {
            Customer customer = customerRepository.findById(order.getCustomer().getCustomerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            order.setCustomer(customer);
        }
        orderRepository.save(order);
    }

    @Override
    public String deleteOrder(final Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        orderRepository.delete(order);
        return "Order with orderId: " + orderId + " deleted successfully !!";
    }

    @Override
    public Order updateOrder(final Order order, final Long orderId) {
        Order savedOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        savedOrder.setOrderDate(order.getOrderDate());
        savedOrder.setOrderItems(order.getOrderItems());
        savedOrder.setTotalAmount(order.getTotalAmount());
        if (order.getCustomer() != null) {
            Customer customer = customerRepository.findById(order.getCustomer().getCustomerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
            savedOrder.setCustomer(customer);
        }
        orderRepository.save(savedOrder);
        return savedOrder;
    }

    @Override
    public List<Order> getOrdersByCustomer(final Long customerId) {
        return orderRepository.findByCustomerCustomerId(customerId);
    }
}
