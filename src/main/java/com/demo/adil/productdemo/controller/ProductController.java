package com.demo.adil.productdemo.controller;

import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;
import com.demo.adil.productdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/private/user/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable(name = "id") Long productId){
        ProductResponseDto productResponseDto = productService.getProduct(productId);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductDto productDto){
        ProductResponseDto productResponseDto = productService.createProduct(productDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable(name = "id") Long productId, @RequestBody @Valid ProductDto productDto){
        ProductResponseDto productResponseDto = productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategoryId(@PathVariable(name = "id") Long categoryId){
        List<ProductResponseDto> products = productService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}