package com.demo.adil.productdemo.service;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategory(Long categoryId);

    CategoryResponseDto createCategory(CategoryDto categoryDto);

    CategoryResponseDto updateCategory(Long categoryId ,CategoryDto categoryDto);

    void deleteCategory(Long categoryId);

}