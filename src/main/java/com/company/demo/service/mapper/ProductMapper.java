package com.company.demo.service.mapper;

import com.company.demo.dto.ProductDto;
import com.company.demo.module.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {


    public Product toEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .amount(dto.getAmount())
                .price(dto.getPrice())
                .colour(dto.getColour())
                .userId(dto.getUserId())
                .build();
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .amount(product.getAmount())
                .price(product.getPrice())
                .colour(product.getColour())
                .userId(product.getUserId())
                .build();
    }

    public List<ProductDto> convertToDto(List<Product> productList){
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product :productList ){
            productDtoList.add(this.toDto(product));
        }

        return productDtoList;
    }
}
