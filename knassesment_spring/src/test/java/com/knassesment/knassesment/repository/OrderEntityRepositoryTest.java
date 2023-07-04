package com.knassesment.knassesment.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.knassesment.knassesment.entity.Customer;
import com.knassesment.knassesment.entity.OrderEntity;
import com.knassesment.knassesment.entity.OrderLine;
import com.knassesment.knassesment.entity.Product;
import com.knassesment.knassesment.repository.OrderEntityRepository;
import com.knassesment.knassesment.repository.ProductRepository;
import com.knassesment.knassesment.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderEntityRepositoryTest {

    @Autowired
    private OrderEntityRepository orderEntityRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void testOrderEntityCRUDOperations() {
        Customer customer = new Customer();
        customer.setFullName("John Doe");
        customer.setEmail("a@b.com");
        customer.setTelephone("1234567890");
        Customer savedCustomer = customerRepository.save(customer);

        Product product = new Product();
        product.setName("Broom");
        product.setSkuCode("sku123");
        product.setUnitPrice(5);
        Product savedProduct = productRepository.save(product);

        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(savedProduct);
        orderLine.setQuantity(2);

        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomer(savedCustomer);
        orderEntity.setSubmissionDate(LocalDate.now());
        orderEntity.setOrderLines(orderLines);

        OrderEntity savedOrderEntity = orderEntityRepository.save(orderEntity);

        Assertions.assertNotNull(savedOrderEntity.getId());
        Assertions.assertEquals(savedCustomer, savedOrderEntity.getCustomer());
        Assertions.assertEquals(LocalDate.now(), savedOrderEntity.getSubmissionDate());
        Assertions.assertEquals(orderLines, savedOrderEntity.getOrderLines());

        OrderEntity foundOrderEntity = orderEntityRepository.findById(savedOrderEntity.getId()).orElse(null);
        Assertions.assertNotNull(foundOrderEntity);
        Assertions.assertEquals(savedOrderEntity, foundOrderEntity);

        foundOrderEntity.setSubmissionDate(LocalDate.now().minusDays(1));
        OrderEntity updatedOrderEntity = orderEntityRepository.save(foundOrderEntity);
        Assertions.assertEquals(LocalDate.now().minusDays(1), updatedOrderEntity.getSubmissionDate());

        List<OrderEntity> allOrders = orderEntityRepository.findAll();
        Assertions.assertFalse(allOrders.isEmpty());

        orderEntityRepository.deleteById(updatedOrderEntity.getId());

        OrderEntity deletedOrderEntity = orderEntityRepository.findById(updatedOrderEntity.getId()).orElse(null);
        Assertions.assertNull(deletedOrderEntity);
    }
}
