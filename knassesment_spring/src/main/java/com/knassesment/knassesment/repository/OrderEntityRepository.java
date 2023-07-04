package com.knassesment.knassesment.repository;

import com.knassesment.knassesment.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity o WHERE DATE(o.submissionDate) = :date")
    List<OrderEntity> searchAllOrdersByDateJPQL(@Param("date") LocalDate date);


}

