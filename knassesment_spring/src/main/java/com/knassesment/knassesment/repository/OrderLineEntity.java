package com.knassesment.knassesment.repository;

import com.knassesment.knassesment.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineEntity extends JpaRepository<OrderLine,Long> {
}
