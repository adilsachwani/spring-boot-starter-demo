package com.demo.adil.productdemo.service.impl;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.mapper.CategoryMapper;
import com.demo.adil.productdemo.models.Category;
import com.demo.adil.productdemo.repository.CategoryRepository;
import com.demo.adil.productdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toListResponseDto(categories);
    }

    @Override
    public CategoryResponseDto getCategory(Long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.fromDto(categoryDto);
        category = categoryRepository.save(category);
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        if(Boolean.FALSE.equals(categoryRepository.existsById(categoryId))){
            throw new RuntimeException("Category doesn't exist");
        }
        Category category = categoryMapper.fromDto(categoryDto);
        category.setCategoryId(categoryId);
        return categoryMapper.toResponseDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}