package com.knassesment.knassesment.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.knassesment.knassesment.entity.Customer;
import com.knassesment.knassesment.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Transactional
    public void testCustomerCRUDOperations() {
        Customer customer = new Customer();
        customer.setFullName("John Doe");
        customer.setEmail("a@b.com");
        customer.setTelephone("1234567890");

        Customer savedCustomer = customerRepository.save(customer);

        Assertions.assertNotNull(savedCustomer.getId());
        Assertions.assertEquals("John Doe", savedCustomer.getFullName());
        Assertions.assertEquals("a@b.com", savedCustomer.getEmail());
        Assertions.assertEquals("1234567890", savedCustomer.getTelephone());

        Optional<Customer> optionalCustomer = customerRepository.findById(savedCustomer.getId());
        Assertions.assertTrue(optionalCustomer.isPresent());
        Customer foundCustomer = optionalCustomer.get();
        Assertions.assertEquals(savedCustomer, foundCustomer);

        foundCustomer.setEmail("b@a.com");
        Customer updatedCustomer = customerRepository.save(foundCustomer);
        Assertions.assertEquals("b@a.com", updatedCustomer.getEmail());

        List<Customer> allCustomers = customerRepository.findAll();
        Assertions.assertFalse(allCustomers.isEmpty());

        customerRepository.deleteById(updatedCustomer.getId());

        Optional<Customer> deletedCustomer = customerRepository.findById(updatedCustomer.getId());
        Assertions.assertFalse(deletedCustomer.isPresent());
    }
}
