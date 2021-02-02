package com.demo.adil.productdemo.service.impl;

import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;
import com.demo.adil.productdemo.models.Category;
import com.demo.adil.productdemo.models.Product;
import com.demo.adil.productdemo.repository.CategoryRepository;
import com.demo.adil.productdemo.repository.ProductRepository;
import com.demo.adil.productdemo.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> products = new ArrayList<>();
        for(Product product : productRepository.findAll()){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            BeanUtils.copyProperties(product, productResponseDto);
            products.add(productResponseDto);
        }
        return products;
    }

    @Override
    public ProductResponseDto getProduct(Long productId) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        BeanUtils.copyProperties(productRepository.getOne(productId), productResponseDto);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto createProduct(ProductDto productDto) {
        Product product = new Product();
        ProductResponseDto productResponseDto = new ProductResponseDto();
        BeanUtils.copyProperties(productDto, product);

        Category category = categoryRepository.getOne(productDto.getCategoryId());
        product.setCategory(category);

        product = productRepository.save(product);
        BeanUtils.copyProperties(product, productResponseDto);

        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(Long productId, ProductDto productDto) {
        Product currentProduct = productRepository.getOne(productId);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        BeanUtils.copyProperties(productDto, currentProduct, "productId");

        if(productDto.getCategoryId()!=null){
            Category category = categoryRepository.getOne(productDto.getCategoryId());
            currentProduct.setCategory(category);
        }

        Product savedProduct = productRepository.save(currentProduct);
        BeanUtils.copyProperties(savedProduct, productResponseDto);

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategoryId(Long categoryId) {
        List<ProductResponseDto> products = new ArrayList<>();
        Category category = categoryRepository.getOne(categoryId);
        for(Product product : productRepository.findByCategory(category)){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            BeanUtils.copyProperties(product, productResponseDto);
            products.add(productResponseDto);
        }
        return products;
    }

}