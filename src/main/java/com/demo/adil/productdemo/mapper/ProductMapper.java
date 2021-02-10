package com.demo.adil.productdemo.mapper;

import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;
import com.demo.adil.productdemo.models.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public ProductDto toDto(Product product);

    public Product fromDto(ProductDto productDto);

    public ProductResponseDto toResponseDto(Product product);

    public List<ProductResponseDto> toListResponseDto(List<Product> products);

}