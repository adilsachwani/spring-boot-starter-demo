package com.demo.adil.productdemo.service;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.models.Category;
import com.demo.adil.productdemo.repository.CategoryRepository;
import com.demo.adil.productdemo.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceUnitTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    public void testGetAllCategory(){
        Mockito.when(categoryRepository.findAll()).thenReturn(stubListOfCategory());
        List<CategoryResponseDto> categories = categoryService.getAllCategories();
        Assertions.assertNotNull(categories);
        Assertions.assertEquals(1, categories.size());
    }

    @Test
    public void testGetCategory(){
        Category category = stubCategory();
        Mockito.when(categoryRepository.getOne(any())).thenReturn(category);
        CategoryResponseDto categoryResponseDto = categoryService.getCategory(1L);
        Assertions.assertNotNull(categoryResponseDto);
        Assertions.assertEquals(1L, categoryResponseDto.getCategoryId());
        Assertions.assertEquals(category.getTitle(), categoryResponseDto.getTitle());
    }

    @Test
    public void testCreateCategory(){
        CategoryDto categoryDto = stubCategoryDto();
        Mockito.when(categoryRepository.save(any())).thenReturn(stubCategory());
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(categoryDto);
        Assertions.assertNotNull(categoryResponseDto);
        Assertions.assertEquals(categoryDto.getTitle(), categoryResponseDto.getTitle());
    }

    @Test
    public void testUpdateCategory(){
        CategoryDto categoryDto = stubCategoryDto();
        Mockito.when(categoryRepository.getOne(any())).thenReturn(stubCategory());
        Mockito.when(categoryRepository.save(any())).thenReturn(stubCategory());
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(1L, categoryDto);
        Assertions.assertNotNull(categoryResponseDto);
        Assertions.assertEquals(categoryDto.getTitle(), categoryResponseDto.getTitle());
    }

    private Category stubCategory(){
        return Category.builder()
                .categoryId(1L)
                .title("Test")
                .build();
    }

    private CategoryDto stubCategoryDto(){
        return CategoryDto.builder()
                .title("Test")
                .build();
    }

    private List<Category> stubListOfCategory(){
        List<Category> categories = new ArrayList<>();
        categories.add(stubCategory());
        return categories;
    }

}