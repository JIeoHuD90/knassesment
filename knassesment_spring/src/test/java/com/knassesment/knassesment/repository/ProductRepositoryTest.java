package com.knassesment.knassesment.repository;

import com.knassesment.knassesment.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import com.knassesment.knassesment.entity.Product;
import com.knassesment.knassesment.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void testProductCRUDOperations() {
        Product product = new Product();
        product.setName("Broom");
        product.setSkuCode("sku123");
        product.setUnitPrice(5.6);

        Product savedProduct = productRepository.save(product);

        Assertions.assertNotNull(savedProduct.getId());
        Assertions.assertEquals("Broom", savedProduct.getName());
        Assertions.assertEquals("sku123", savedProduct.getSkuCode());
        Assertions.assertEquals(5.6, savedProduct.getUnitPrice());

        Optional<Product> optionalProduct = productRepository.findById(savedProduct.getId());
        Assertions.assertTrue(optionalProduct.isPresent());
        Product foundProduct = optionalProduct.get();
        Assertions.assertEquals(savedProduct, foundProduct);

        foundProduct.setUnitPrice(6);
        Product updatedProduct = productRepository.save(foundProduct);
        Assertions.assertEquals(6, updatedProduct.getUnitPrice());

        List<Product> allProducts = productRepository.findAll();
        Assertions.assertFalse(allProducts.isEmpty());

        productRepository.deleteById(updatedProduct.getId());

        Optional<Product> deletedProduct = productRepository.findById(updatedProduct.getId());
        Assertions.assertFalse(deletedProduct.isPresent());
    }
}
