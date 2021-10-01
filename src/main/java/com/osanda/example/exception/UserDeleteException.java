package com.osanda.example.exception;

public class UserDeleteException extends RuntimeException {
    private static final long serialVersionUID = -6590212377267910822L;

    public UserDeleteException(String message) {
        super(message);
    }
}
