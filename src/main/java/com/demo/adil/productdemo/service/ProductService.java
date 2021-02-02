package com.demo.adil.productdemo.service;

import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto getProduct(Long productId);

    ProductResponseDto createProduct(ProductDto productDto);

    ProductResponseDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);

    List<ProductResponseDto> getProductsByCategoryId(Long categoryId);

}