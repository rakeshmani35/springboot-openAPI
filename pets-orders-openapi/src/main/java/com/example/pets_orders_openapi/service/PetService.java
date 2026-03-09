package com.example.pets_orders_openapi.service;

import com.example.pets_orders_openapi.entity.PetEntity;
import com.example.pets_orders_openapi.model.NewPet;
import com.example.pets_orders_openapi.model.Pet;
import com.example.pets_orders_openapi.repo.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository repo;

    public PetService(PetRepository repo) {
        this.repo = repo;
    }

    public Pet create(NewPet newPet) {
        PetEntity e = new PetEntity();
        e.setName(newPet.getName());
        e.setType(newPet.getType());

        PetEntity saved = repo.save(e);
        return toModel(saved);
    }

    public List<Pet> findAll() {
        return repo.findAll().stream().map(this::toModel).toList();
    }

    public Pet findById(Long id) {
        return repo.findById(id)
                .map(this::toModel)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    //----- Mapper -----
    private Pet toModel(PetEntity e) {
        Pet p = new Pet();
        p.setId(e.getId());
        p.setName(e.getName());
        p.setType(e.getType());
        return p;
    }
}
