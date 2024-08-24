package com.ecommmerce.project.controller;

import com.ecommmerce.project.model.Order;
import com.ecommmerce.project.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/public/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/public/orders/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/admin/orders")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        try {
            String status = orderService.deleteOrder(orderId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/admin/orders/{orderId}")
    public ResponseEntity<String> updateOrder(@RequestBody Order order, @PathVariable Long orderId) {
        try {
            Order savedOrder = orderService.updateOrder(order, orderId);
            return new ResponseEntity<>("Order with orderId: " + savedOrder.getOrderId() + " updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
