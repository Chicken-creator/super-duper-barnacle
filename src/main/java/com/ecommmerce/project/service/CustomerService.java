package com.ecommmerce.project.service;

import com.ecommmerce.project.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Long customerId);
    void createCustomer(Customer customer);
    String deleteCustomer(Long customerId);
    Customer updateCustomer(Customer customer, Long customerId);
}
