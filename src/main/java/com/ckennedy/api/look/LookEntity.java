package com.ckennedy.api.look;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "looks")
public class LookEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String name;

    private String subtitle;

    @Column(name = "display_date")
    private String date;

    private String deliveryTag;

    private String badge;

    @Column(name = "hero_image", columnDefinition = "TEXT")
    private String heroImage;

    private String topLeftTitle;
    private String topLeftSubtitle;

    private String topRightTitle;
    private String topRightSubtitle;

    private String bottomLeftTitle;
    private String bottomLeftSubtitle;

    private String bottomRightTitle;
    private String bottomRightSubtitle;

    @ElementCollection
    @CollectionTable(
            name = "look_product_ids",
            joinColumns = @JoinColumn(name = "look_id")
    )
    @Column(name = "product_id")
    @OrderColumn(name = "position")
    private List<String> includedProductIds =
            new ArrayList<>();

    public LookEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeliveryTag() {
        return deliveryTag;
    }

    public void setDeliveryTag(String deliveryTag) {
        this.deliveryTag = deliveryTag;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(String heroImage) {
        this.heroImage = heroImage;
    }

    public String getTopLeftTitle() {
        return topLeftTitle;
    }

    public void setTopLeftTitle(String topLeftTitle) {
        this.topLeftTitle = topLeftTitle;
    }

    public String getTopLeftSubtitle() {
        return topLeftSubtitle;
    }

    public void setTopLeftSubtitle(String topLeftSubtitle) {
        this.topLeftSubtitle = topLeftSubtitle;
    }

    public String getTopRightTitle() {
        return topRightTitle;
    }

    public void setTopRightTitle(String topRightTitle) {
        this.topRightTitle = topRightTitle;
    }

    public String getTopRightSubtitle() {
        return topRightSubtitle;
    }

    public void setTopRightSubtitle(String topRightSubtitle) {
        this.topRightSubtitle = topRightSubtitle;
    }

    public String getBottomLeftTitle() {
        return bottomLeftTitle;
    }

    public void setBottomLeftTitle(String bottomLeftTitle) {
        this.bottomLeftTitle = bottomLeftTitle;
    }

    public String getBottomLeftSubtitle() {
        return bottomLeftSubtitle;
    }

    public void setBottomLeftSubtitle(String bottomLeftSubtitle) {
        this.bottomLeftSubtitle = bottomLeftSubtitle;
    }

    public String getBottomRightTitle() {
        return bottomRightTitle;
    }

    public void setBottomRightTitle(String bottomRightTitle) {
        this.bottomRightTitle = bottomRightTitle;
    }

    public String getBottomRightSubtitle() {
        return bottomRightSubtitle;
    }

    public void setBottomRightSubtitle(String bottomRightSubtitle) {
        this.bottomRightSubtitle = bottomRightSubtitle;
    }

    public List<String> getIncludedProductIds() {
        return includedProductIds;
    }

    public void setIncludedProductIds(List<String> includedProductIds) {
        this.includedProductIds = includedProductIds;
    }
}
