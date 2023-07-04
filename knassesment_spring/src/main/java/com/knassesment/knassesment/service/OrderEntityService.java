package com.knassesment.knassesment.service;

import com.knassesment.knassesment.entity.OrderEntity;
import com.knassesment.knassesment.entity.OrderLine;
import com.knassesment.knassesment.repository.OrderEntityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderEntityService {
    private final OrderEntityRepository orderEntityRepository;

    public OrderEntityService(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    public OrderEntity createOrder(OrderEntity orderEntity) {

        return orderEntityRepository.save(orderEntity);
    }

    public List<OrderEntity> searchAllOrdersByDateJPQL(LocalDate date) {
        return orderEntityRepository.searchAllOrdersByDateJPQL(date);
    }


}
