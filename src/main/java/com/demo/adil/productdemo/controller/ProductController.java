package com.demo.adil.productdemo.controller;

import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;
import com.demo.adil.productdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/private/user/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable(name = "id") Long productId){
        return productService.getProduct(productId);
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable(name = "id") Long productId, @RequestBody ProductDto productDto){
        return productService.updateProduct(productId, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long productId){
        productService.deleteProduct(productId);
    }

    @GetMapping("/category/{id}")
    public List<ProductResponseDto> getProductsByCategoryId(@PathVariable(name = "id") Long categoryId){
        return productService.getProductsByCategoryId(categoryId);
    }

}