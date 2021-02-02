package com.demo.adil.productdemo.controller;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.models.Category;
import com.demo.adil.productdemo.repository.CategoryRepository;
import com.demo.adil.productdemo.service.CategoryService;
import com.demo.adil.productdemo.service.impl.CategoryServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("{id}")
    public CategoryResponseDto getCategory(@PathVariable(name = "id") Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }

    @PutMapping("{id}")
    public CategoryResponseDto updateCategory(@PathVariable(name = "id") Long categoryId, @RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(categoryId, categoryDto);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable(name = "id") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }

}