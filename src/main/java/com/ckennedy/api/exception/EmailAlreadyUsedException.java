package com.ckennedy.api.exception;

public class EmailAlreadyUsedException
        extends RuntimeException {

    public EmailAlreadyUsedException() {
        super("Cette adresse email est déjà utilisée.");
    }
}
