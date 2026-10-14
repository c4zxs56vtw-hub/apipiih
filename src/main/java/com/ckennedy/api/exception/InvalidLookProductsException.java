package com.ckennedy.api.exception;

public class InvalidLookProductsException
        extends RuntimeException {

    public InvalidLookProductsException() {
        super("Un ou plusieurs produits du look sont introuvables.");
    }
}
