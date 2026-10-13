package com.ckennedy.api.exception;

public class UnauthorizedException
        extends RuntimeException {

    public UnauthorizedException() {
        super("Authentification requise.");
    }
}
