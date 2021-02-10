package com.demo.adil.productdemo.mapper;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category fromDto(CategoryDto categoryDto);

    CategoryResponseDto toResponseDto(Category category);

    List<CategoryResponseDto> toListResponseDto(List<Category> categories);

}