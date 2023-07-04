package com.knassesment.knassesment.API;

import com.knassesment.knassesment.entity.OrderEntity;
import com.knassesment.knassesment.service.OrderEntityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderEntityController {
    private final OrderEntityService orderEntityService;

    public OrderEntityController(OrderEntityService orderEntityService) {
        this.orderEntityService = orderEntityService;
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
        OrderEntity createdOrder = orderEntityService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }



    @GetMapping("/search")
    public ResponseEntity<List<OrderEntity>> searchOrdersByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<OrderEntity> orders = orderEntityService.searchAllOrdersByDateJPQL(date);
        return ResponseEntity.ok(orders);
    }
}
