package com.spiny.spiny_demo.Exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

}
