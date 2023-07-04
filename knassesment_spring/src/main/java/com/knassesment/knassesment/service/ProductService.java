package com.knassesment.knassesment.service;

import com.knassesment.knassesment.entity.OrderEntity;
import com.knassesment.knassesment.entity.Product;
import com.knassesment.knassesment.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


}
