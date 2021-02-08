package com.demo.adil.productdemo.controller;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/private/admin/v1/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable(name = "id") Long categoryId){
        CategoryResponseDto categoryResponseDto = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Valid CategoryDto categoryDto){
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable(name = "id") Long categoryId, @RequestBody @Valid CategoryDto categoryDto){
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryId, categoryDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}