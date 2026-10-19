package com.ckennedy.api.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductImagesEntity {

    @Column(name = "image_primary", nullable = false, columnDefinition = "TEXT")
    private String primary;

    @Column(name = "image_secondary", columnDefinition = "TEXT")
    private String secondary;

    @Column(name = "image_detail", columnDefinition = "TEXT")
    private String detail;

    @Column(name = "cart_thumbnail", nullable = false, columnDefinition = "TEXT")
    private String cartThumbnail;

    public ProductImagesEntity() {
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCartThumbnail() {
        return cartThumbnail;
    }

    public void setCartThumbnail(String cartThumbnail) {
        this.cartThumbnail = cartThumbnail;
    }
}
