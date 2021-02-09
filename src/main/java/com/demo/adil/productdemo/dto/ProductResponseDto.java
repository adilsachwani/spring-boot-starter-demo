package com.demo.adil.productdemo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private Long productId;

    private String title;

    private String description;

    private Integer price;

    private CategoryResponseDto category;

}