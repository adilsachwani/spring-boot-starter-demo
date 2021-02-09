package com.demo.adil.productdemo.controller;

import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.dto.ProductDto;
import com.demo.adil.productdemo.dto.ProductResponseDto;
import com.demo.adil.productdemo.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @InjectMocks
    ProductController productController;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "ADMIN")
    public void testGetAllProduct() throws Exception {
        List<ProductResponseDto> products = stubListOfProductResponseDto();
        Mockito.when(productService.getAllProducts()).thenReturn(products);
        String responseJSON = new ObjectMapper().writeValueAsString(products);
        mockMvc.perform(get("/api/private/user/v1/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJSON))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "ADMIN")
    public void testGetProduct() throws Exception {
        ProductResponseDto productResponseDto = stubProductResponseDto();
        Mockito.when(productService.getProduct(any())).thenReturn(productResponseDto);
        mockMvc.perform(get("/api/private/user/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is(productResponseDto.getTitle())))
                .andExpect(jsonPath("$.description", Matchers.is(productResponseDto.getDescription())))
                .andExpect(jsonPath("$.price", Matchers.is(productResponseDto.getPrice())))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "ADMIN")
    public void testCreateProduct() throws Exception {
        ProductResponseDto productResponseDto = stubProductResponseDto();
        Mockito.when(productService.createProduct(any())).thenReturn(productResponseDto);
        String requestJSON = new ObjectMapper().writeValueAsString(stubProductDto());
        mockMvc.perform(post("/api/private/user/v1/products")
                .content(requestJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", Matchers.is(productResponseDto.getTitle())))
                .andExpect(jsonPath("$.description", Matchers.is(productResponseDto.getDescription())))
                .andExpect(jsonPath("$.price", Matchers.is(productResponseDto.getPrice())))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "ADMIN")
    public void testUpdateProduct() throws Exception {
        ProductResponseDto productResponseDto = stubProductResponseDto();
        Mockito.when(productService.updateProduct(any(), any())).thenReturn(productResponseDto);
        String requestJSON = new ObjectMapper().writeValueAsString(stubProductDto());
        mockMvc.perform(put("/api/private/user/v1/products/1")
                .content(requestJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", Matchers.is(productResponseDto.getTitle())))
                .andExpect(jsonPath("$.description", Matchers.is(productResponseDto.getDescription())))
                .andExpect(jsonPath("$.price", Matchers.is(productResponseDto.getPrice())))
                .andDo(print());
    }

    private ProductDto stubProductDto(){
        return ProductDto.builder()
                .title("Bat")
                .description("Bat Description")
                .price(1000)
                .categoryId(1L)
                .build();
    }

    private ProductResponseDto stubProductResponseDto(){
        CategoryResponseDto categoryResponseDto = CategoryResponseDto.builder()
                .categoryId(1L)
                .title("Cricket")
                .build();
        return ProductResponseDto.builder()
                .productId(1L)
                .title("Bat")
                .description("Bat Description")
                .price(1000)
                .category(categoryResponseDto)
                .build();
    }

    private List<ProductResponseDto> stubListOfProductResponseDto(){
        List<ProductResponseDto> products = new ArrayList<>();
        products.add(stubProductResponseDto());
        return products;
    }

}