package com.demo.adil.productdemo.mapper;

import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;
import com.demo.adil.productdemo.models.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product fromDto(ProductDto productDto);

    ProductResponseDto toResponseDto(Product product);

    List<ProductResponseDto> toListResponseDto(List<Product> products);

}