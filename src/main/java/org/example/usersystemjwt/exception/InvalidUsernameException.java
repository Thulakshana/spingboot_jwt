package org.example.usersystemjwt.exception;

public class InvalidUsernameException
        extends RuntimeException {

    public InvalidUsernameException(String message) {
        super(message);
    }
}