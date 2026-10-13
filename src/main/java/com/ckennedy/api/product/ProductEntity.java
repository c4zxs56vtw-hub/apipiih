package com.ckennedy.api.product;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String name;

    private String subtitle = "";

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long price;

    private String season = "";

    private String badge = "";

    @Embedded
    private ProductImagesEntity images;

    @ElementCollection
    @CollectionTable(
            name = "product_sizes",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "size")
    @OrderColumn(name = "size_order")
    private List<String> sizes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "product_colors",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @OrderColumn(name = "color_order")
    private List<ProductColorEntity> colors = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String description = "";

    @ElementCollection
    @CollectionTable(
            name = "product_specs",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "spec")
    @OrderColumn(name = "spec_order")
    private List<String> specs = new ArrayList<>();

    @Column(name = "in_stock", nullable = false)
    private Boolean inStock = true;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished = true;

    @Column(name = "likes_count", nullable = false)
    private Long likesCount = 0L;

    private Double rating = 0.0;

    @Column(name = "reviews_count")
    private Integer reviewsCount = 0;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;


    @PrePersist
    public void onCreate() {

        if (id == null) {
            id = UUID.randomUUID().toString();
        }

        OffsetDateTime now =
                OffsetDateTime.now(ZoneOffset.UTC);

        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt =
                OffsetDateTime.now(ZoneOffset.UTC);
    }

    public ProductEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public ProductImagesEntity getImages() {
        return images;
    }

    public void setImages(ProductImagesEntity images) {
        this.images = images;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<ProductColorEntity> getColors() {
        return colors;
    }

    public void setColors(List<ProductColorEntity> colors) {
        this.colors = colors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSpecs() {
        return specs;
    }

    public void setSpecs(List<String> specs) {
        this.specs = specs;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
