package com.demo.adil.productdemo.service.impl;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.models.Category;
import com.demo.adil.productdemo.repository.CategoryRepository;
import com.demo.adil.productdemo.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<CategoryResponseDto> categories = new ArrayList<>();
        for(Category category : categoryRepository.findAll()){
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
            BeanUtils.copyProperties(category, categoryResponseDto);
            categories.add(categoryResponseDto);
        }
        return categories;
    }

    @Override
    public CategoryResponseDto getCategory(Long categoryId) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        Category category = categoryRepository.getOne(categoryId);
        BeanUtils.copyProperties(category, categoryResponseDto);
        return categoryResponseDto;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(categoryDto, category);

        category = categoryRepository.save(category);
        BeanUtils.copyProperties(category, categoryResponseDto);

        return categoryResponseDto;
    }

    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category currentCategory = categoryRepository.getOne(categoryId);
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(categoryDto, currentCategory, "categoryId");

        Category updatedCategory = categoryRepository.saveAndFlush(currentCategory);
        BeanUtils.copyProperties(updatedCategory, categoryResponseDto);
        return categoryResponseDto;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}