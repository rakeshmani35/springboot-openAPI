package com.springboot.openapi.generator.service;

import com.springboot.openapi.generator.dto.UserRequestDTO;
import com.springboot.openapi.generator.dto.UserResponseDTO;
import com.springboot.openapi.generator.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<UUID, UserRequestDTO> db = new ConcurrentHashMap<>();

    public UserResponseDTO saveUser(UserRequestDTO userDTO) {
        UUID uuid = UUID.randomUUID();
        userDTO.setId(uuid);
        db.put(uuid, userDTO);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userDTO.getId());
        userResponseDTO.setEmail(userDTO.getEmail());
        userResponseDTO.setFirstName(userDTO.getFirstName());
        userResponseDTO.setFirstName(userDTO.getLastName());

        return userResponseDTO;
    }

    public UserResponseDTO getUser(UUID id) throws UserNotFoundException {

        UserRequestDTO userDTO = Optional.ofNullable(db.get(id))
                .orElseThrow(() -> new UserNotFoundException("User not found for Id: " + id));

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userDTO.getId());
        userResponseDTO.setEmail(userDTO.getEmail());
        userResponseDTO.setFirstName(userDTO.getFirstName());
        userResponseDTO.setFirstName(userDTO.getLastName());

        return userResponseDTO;
    }

}