package com.example.demo.Product;

import com.example.demo.Category.CategoryEntity;

import java.math.BigDecimal;

public class ProductDTO {
    public final String name;
    public final double cost;
    public final String longDescription;
    public final String shortDescription;
    public final long stock;
    public final String imageUrl;
    public final CategoryEntity category;

    public ProductDTO(String name, double cost, String longDescription, String shortDescription, Long stock, String imageUrl, CategoryEntity category) {
        this.name = name;
        this.cost = cost;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public long getStock() {
        return stock;
    }

    public String getImage() {
        return imageUrl;
    }

    public CategoryEntity getCategory() {
        return category;
    }
}