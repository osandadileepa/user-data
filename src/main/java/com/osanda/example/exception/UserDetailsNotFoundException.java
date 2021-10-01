package com.osanda.example.exception;

public class UserDetailsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5060915832798793799L;

    public UserDetailsNotFoundException(String message) {
        super(message);
    }
}
