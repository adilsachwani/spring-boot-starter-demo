package com.demo.adil.productdemo.service;

import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;
import com.demo.adil.productdemo.models.Category;
import com.demo.adil.productdemo.models.Product;
import com.demo.adil.productdemo.repository.CategoryRepository;
import com.demo.adil.productdemo.repository.ProductRepository;
import com.demo.adil.productdemo.service.impl.ProductServiceImpl;
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
public class ProductServiceUnitTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void testGetAllProduct(){
        Mockito.when(productRepository.findAll()).thenReturn(stubListOfProduct());
        List<ProductResponseDto> products = productService.getAllProducts();
        Assertions.assertNotNull(products);
        Assertions.assertEquals(1L, products.size());
    }

    @Test
    public void testGetProduct(){
        Product product = stubProduct();
        Mockito.when(productRepository.getOne(any())).thenReturn(product);
        ProductResponseDto productResponseDto = productService.getProduct(1L);
        Assertions.assertNotNull(productResponseDto);
        Assertions.assertEquals(product.getTitle(), productResponseDto.getTitle());
    }

    @Test
    public void testCreateProduct(){
        ProductDto productDto = stubProductDto();
        Mockito.when(productRepository.save(any())).thenReturn(stubProduct());
        Mockito.when(categoryRepository.getOne(any())).thenReturn(new Category());
        ProductResponseDto productResponseDto = productService.createProduct(productDto);
        Assertions.assertNotNull(productResponseDto);
        Assertions.assertEquals(productDto.getTitle(), productResponseDto.getTitle());
    }

    @Test
    public void testUpdateProduct(){
        ProductDto productDto = stubProductDto();
        Mockito.when(productRepository.save(any())).thenReturn(stubProduct());
        Mockito.when(productRepository.getOne(any())).thenReturn(new Product());
        Mockito.when(categoryRepository.getOne(any())).thenReturn(new Category());
        ProductResponseDto productResponseDto = productService.updateProduct(1L, productDto);
        Assertions.assertNotNull(productResponseDto);
        Assertions.assertEquals(productDto.getTitle(), productResponseDto.getTitle());
    }

    Product stubProduct(){
        Category category = Category.builder()
                .categoryId(1L)
                .title("Cricket")
                .build();
        return Product.builder()
                .title("Bat")
                .description("Bat Description")
                .price(1000)
                .category(category)
                .build();
    }

    ProductDto stubProductDto(){
        return ProductDto.builder()
                .title("Bat")
                .description("Bat Description")
                .price(100)
                .categoryId(1L)
                .build();
    }

    List<Product> stubListOfProduct(){
        List<Product> products = new ArrayList<>();
        products.add(stubProduct());
        return products;
    }

}