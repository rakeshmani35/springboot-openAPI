package com.springboot.openapi.generator.exception;

import org.springframework.stereotype.Service;

//@Service
public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }
}
