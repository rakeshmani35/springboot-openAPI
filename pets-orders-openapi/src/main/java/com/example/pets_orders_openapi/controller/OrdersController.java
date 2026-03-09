package com.example.pets_orders_openapi.controller;

import com.example.pets_orders_openapi.api.OrdersApi;
import com.example.pets_orders_openapi.model.NewOrder;
import com.example.pets_orders_openapi.model.Order;
import com.example.pets_orders_openapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController implements OrdersApi {

    private final OrderService service;

    public OrdersController(OrderService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Order> createOrder(NewOrder newOrder) {
        return ResponseEntity.ok(service.create(newOrder));
    }

    @Override
    public ResponseEntity<List<Order>> listOrders() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<Order> getOrderById(Long orderId) {
        return ResponseEntity.ok(service.findById(orderId));
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long orderId) {
        service.delete(orderId);
        return ResponseEntity.noContent().build();
    }
}
