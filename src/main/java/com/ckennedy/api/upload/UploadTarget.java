package com.ckennedy.api.upload;

public enum UploadTarget {

    HERO("hero"),
    PRODUCTS("products"),
    LOOKBOOK("lookbook");

    private final String value;

    UploadTarget(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UploadTarget fromValue(String value) {

        for (UploadTarget target : values()) {
            if (target.value.equalsIgnoreCase(value)) {
                return target;
            }
        }

        throw new IllegalArgumentException(
                "Target d'upload invalide."
        );
    }
}
