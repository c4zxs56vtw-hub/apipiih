package com.ckennedy.api.exception;

public class ForbiddenException
        extends RuntimeException {

    public ForbiddenException() {
        super("Accès réservé aux administrateurs.");
    }
}
