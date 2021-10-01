package com.osanda.example.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3025502078575007654L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
