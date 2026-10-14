package com.ckennedy.api.exception;

public class LookNotFoundException extends RuntimeException {

    public LookNotFoundException() {
        super("Look introuvable.");
    }
}
