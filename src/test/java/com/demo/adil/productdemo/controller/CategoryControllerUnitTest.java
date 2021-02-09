package com.demo.adil.productdemo.controller;

import com.demo.adil.productdemo.dto.CategoryDto;
import com.demo.adil.productdemo.dto.CategoryResponseDto;
import com.demo.adil.productdemo.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    @WithMockUser(username = "user", password = "password", roles = "ADMIN")
    public void testGetAllCategory() throws Exception {
        List<CategoryResponseDto> categories = stubListOfCategoryResponseDto();
        when(categoryService.getAllCategories()).thenReturn(categories);
        String responseJSON = new ObjectMapper().writeValueAsString(categories);
        mockMvc.perform(get("/api/private/admin/v1/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJSON))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "ADMIN")
    public void testGetCategory() throws Exception {
        CategoryResponseDto categoryResponseDto = stubCategoryResponseDto();
        when(categoryService.getCategory(any())).thenReturn(categoryResponseDto);
        mockMvc.perform(get("/api/private/admin/v1/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is(categoryResponseDto.getTitle())))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "ADMIN")
    public void testCreateCategory() throws Exception {
        CategoryResponseDto categoryResponseDto = stubCategoryResponseDto();
        when(categoryService.createCategory(any())).thenReturn(categoryResponseDto);
        String requestJSON = new ObjectMapper().writeValueAsString(stubCategoryDto());
        mockMvc.perform(post("/api/private/admin/v1/categories")
                .content(requestJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", Matchers.is(categoryResponseDto.getTitle())))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "ADMIN")
    public void testUpdateCategory() throws Exception {
        CategoryResponseDto categoryResponseDto = stubCategoryResponseDto();
        when(categoryService.updateCategory(any(), any())).thenReturn(categoryResponseDto);
        String requestJSON = new ObjectMapper().writeValueAsString(stubCategoryDto());
        mockMvc.perform(put("/api/private/admin/v1/categories/1")
                .content(requestJSON)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", Matchers.is(categoryResponseDto.getTitle())))
                .andDo(print());
    }

    private CategoryResponseDto stubCategoryResponseDto(){
        return CategoryResponseDto.builder()
                .categoryId(1L)
                .title("Cricket")
                .build();
    }

    private CategoryDto stubCategoryDto(){
        return CategoryDto.builder()
                .title("Cricket")
                .build();
    }

    private List<CategoryResponseDto> stubListOfCategoryResponseDto(){
        List<CategoryResponseDto> categories = new ArrayList<>();
        categories.add(stubCategoryResponseDto());
        return categories;
    }

}