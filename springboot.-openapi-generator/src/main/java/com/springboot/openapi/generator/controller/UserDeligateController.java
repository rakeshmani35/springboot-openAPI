package com.springboot.openapi.generator.controller;

import com.springboot.openapi.generator.api.UserApiDelegate;
import com.springboot.openapi.generator.dto.UserRequestDTO;
import com.springboot.openapi.generator.dto.UserResponseDTO;
import com.springboot.openapi.generator.exception.UserNotFoundException;
import com.springboot.openapi.generator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDeligateController implements UserApiDelegate {

    private final UserService userService;

    public UserDeligateController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponseDTO> saveUser(UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @Override
    public ResponseEntity<UserResponseDTO> getUserById(UUID id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
