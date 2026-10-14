package com.ckennedy.api.exception;

public class InvalidLimitException extends RuntimeException {

    public InvalidLimitException() {
        super("La limite doit être supérieure ou égale à 1.");
    }
}
