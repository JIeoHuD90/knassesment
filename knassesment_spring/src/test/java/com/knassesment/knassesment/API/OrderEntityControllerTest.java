package com.knassesment.knassesment.API;

import com.knassesment.knassesment.controller.OrderEntityController;
import com.knassesment.knassesment.entity.OrderEntity;
import com.knassesment.knassesment.service.OrderEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderEntityControllerTest {
    @Mock
    private OrderEntityService orderEntityService;

    @InjectMocks
    private OrderEntityController orderEntityController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrder() {
        OrderEntity orderEntity = new OrderEntity();
        OrderEntity createdOrderEntity = new OrderEntity();
        when(orderEntityService.createOrder(orderEntity)).thenReturn(createdOrderEntity);
        ResponseEntity<OrderEntity> response = orderEntityController.createOrder(orderEntity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdOrderEntity, response.getBody());

        verify(orderEntityService, times(1)).createOrder(orderEntity);
    }

    @Test
    public void testSearchOrdersByDate() {
        LocalDate date = LocalDate.now();
        List<OrderEntity> orders = new ArrayList<>();
        when(orderEntityService.searchAllOrdersByDateJPQL(date)).thenReturn(orders);
        ResponseEntity<List<OrderEntity>> response = orderEntityController.searchOrdersByDate(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());

        verify(orderEntityService, times(1)).searchAllOrdersByDateJPQL(date);
    }
}
