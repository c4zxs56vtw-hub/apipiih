package com.ckennedy.api.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Produit introuvable.");
    }
}
