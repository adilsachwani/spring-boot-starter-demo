package com.demo.adil.productdemo.dto;

import com.demo.adil.productdemo.models.Category;
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

    private Category category;

}
