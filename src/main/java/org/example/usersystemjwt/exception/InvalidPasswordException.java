package org.example.usersystemjwt.exception;

public class InvalidPasswordException
        extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}