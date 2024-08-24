package com.ecommmerce.project.service;

import com.ecommmerce.project.model.Customer;
import com.ecommmerce.project.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(final Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @Override
    public void createCustomer(final Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public String deleteCustomer(final Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        customerRepository.delete(customer);
        return "Customer with customerId: " + customerId + " deleted successfully !!";
    }

    @Override
    public Customer updateCustomer(final Customer customer, final Long customerId) {
        Customer savedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        savedCustomer.setName(customer.getName());
        savedCustomer.setEmail(customer.getEmail());
        savedCustomer.setAddress(customer.getAddress());
        customerRepository.save(savedCustomer);
        return savedCustomer;
    }
}
