package com.ecommmerce.project.controller;

import com.ecommmerce.project.model.Customer;
import com.ecommmerce.project.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/public/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/public/customers/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {

            Customer customer = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK);

    }

    @PostMapping("/admin/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/customers/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        try {
            String status = customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/admin/customers/{customerId}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
        try {
            Customer savedCustomer = customerService.updateCustomer(customer, customerId);
            return new ResponseEntity<>("Customer with customerId: " + savedCustomer.getCustomerId() + " updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
