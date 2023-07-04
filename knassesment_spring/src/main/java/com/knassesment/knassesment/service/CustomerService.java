package com.knassesment.knassesment.service;

import com.knassesment.knassesment.entity.Customer;
import com.knassesment.knassesment.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {

        if (customer.getRegistrationCode() == null || customer.getRegistrationCode().isEmpty()) {
            String registrationCode = generateUniqueRegistrationCode();
            customer.setRegistrationCode(registrationCode);
        }

        return customerRepository.save(customer);
    }

    private String generateUniqueRegistrationCode() {
        return UUID.randomUUID().toString();
    }

}
