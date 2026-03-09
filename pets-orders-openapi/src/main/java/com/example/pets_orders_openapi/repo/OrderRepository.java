package com.example.pets_orders_openapi.repo;

import com.example.pets_orders_openapi.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}

