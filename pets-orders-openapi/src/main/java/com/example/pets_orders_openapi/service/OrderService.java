package com.example.pets_orders_openapi.service;

import com.example.pets_orders_openapi.entity.OrderEntity;
import com.example.pets_orders_openapi.model.NewOrder;
import com.example.pets_orders_openapi.model.Order;
import com.example.pets_orders_openapi.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Order create(NewOrder newOrder) {
        OrderEntity entity = new OrderEntity();
        entity.setPetId(newOrder.getPetId());
        entity.setQuantity(newOrder.getQuantity());

        OrderEntity saved = repo.save(entity);
        return toModel(saved);
    }

    // READ ALL
    public List<Order> findAll() {
        return repo.findAll()
                .stream()
                .map(this::toModel)
                .toList();
    }

    // READ BY ID
    public Order findById(Long id) {
        return repo.findById(id)
                .map(this::toModel)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    // DELETE
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Order not found: " + id);
        }
        repo.deleteById(id);
    }

    // ---- mapping ----
    private Order toModel(OrderEntity e) {
        Order o = new Order();
        o.setId(e.getId());
        o.setPetId(e.getPetId());
        o.setQuantity(e.getQuantity());
        return o;
    }
}

