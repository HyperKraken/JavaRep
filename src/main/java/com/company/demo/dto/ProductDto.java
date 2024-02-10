package com.company.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private Integer price;
    private Integer amount;
    private String colour;

    private Integer userId;
}
