package com.demo.adil.productdemo.dto;

import com.demo.adil.productdemo.models.Product;

import java.util.List;

public class CategoryResponseDto {

    private Long categoryId;
    private String title;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}