package com.demo.adil.productdemo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    @NotBlank(message = "Title must not be blank")
    private String title;

    private String description;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be greater than zero")
    private Integer price;

    @NotNull(message = "Category Id must not be null")
    @Positive(message = "Category Id must be greater than zero")
    private Long categoryId;

}