package com.example.pets_orders_openapi.repo;


import com.example.pets_orders_openapi.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetEntity, Long> {}
