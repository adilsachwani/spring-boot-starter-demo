package com.demo.adil.productdemo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private String title;

    private String description;

    private Integer price;

    private Long categoryId;

}