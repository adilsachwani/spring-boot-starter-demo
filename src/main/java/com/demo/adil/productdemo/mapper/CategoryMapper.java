package com.demo.adil.productdemo.mapper;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public CategoryDto toDto(Category category);

    public Category fromDto(CategoryDto categoryDto);

    public CategoryResponseDto toResponseDto(Category category);

    public List<CategoryResponseDto> toListResponseDto(List<Category> categories);

}