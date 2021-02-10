package com.demo.adil.productdemo.service.impl;

import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;
import com.demo.adil.productdemo.mapper.ProductMapper;
import com.demo.adil.productdemo.models.Category;
import com.demo.adil.productdemo.models.Product;
import com.demo.adil.productdemo.repository.CategoryRepository;
import com.demo.adil.productdemo.repository.ProductRepository;
import com.demo.adil.productdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toListResponseDto(products);
    }

    @Override
    public ProductResponseDto getProduct(Long productId) {
        Product product = productRepository.getOne(productId);
        return productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto createProduct(ProductDto productDto) {
        Product product = productMapper.fromDto(productDto);
        Category category = categoryRepository.getOne(productDto.getCategoryId());
        product.setCategory(category);
        product = productRepository.save(product);
        return productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long productId, ProductDto productDto) {
        if(Boolean.FALSE.equals(productRepository.existsById(productId))){
            throw new RuntimeException("Product doesn't exist.");
        };
        Product product = productMapper.fromDto(productDto);
        Category category = categoryRepository.getOne(productDto.getCategoryId());
        product.setProductId(productId);
        product.setCategory(category);
        product = productRepository.save(product);
        return productMapper.toResponseDto(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryId(Long categoryId) {
        Category category = categoryRepository.getOne(categoryId);
        List<Product> products = productRepository.findByCategory(category);
        return productMapper.toListResponseDto(products);
    }

}