package com.knassesment.knassesment.API;

import com.knassesment.knassesment.controller.CustomerController;
import com.knassesment.knassesment.entity.Customer;
import com.knassesment.knassesment.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        Customer createdCustomer = new Customer();
        when(customerService.createCustomer(customer)).thenReturn(createdCustomer);
        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCustomer, response.getBody());

        verify(customerService, times(1)).createCustomer(customer);
    }
}
