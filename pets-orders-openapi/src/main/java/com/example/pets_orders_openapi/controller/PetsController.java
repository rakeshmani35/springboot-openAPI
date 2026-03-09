package com.example.pets_orders_openapi.controller;


import com.example.pets_orders_openapi.api.PetsApi;
import com.example.pets_orders_openapi.model.NewPet;
import com.example.pets_orders_openapi.model.Pet;
import com.example.pets_orders_openapi.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetsController implements PetsApi {

    private final PetService service;

    public PetsController(PetService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Pet> createPet(NewPet newPet) {
        return ResponseEntity.ok(service.create(newPet));
    }

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return ResponseEntity.ok(service.findById(petId));
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(Integer limit) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePet(Long petId) {
        service.delete(petId);
        return ResponseEntity.noContent().build();
    }
}

