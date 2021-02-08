package com.demo.adil.productdemo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    @NotBlank(message = "Title must not be blank")
    private String title;

}