package com.company.demo.service;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.ErrorDto;
import com.company.demo.dto.ProductDto;
import com.company.demo.module.Product;
import com.company.demo.service.mapper.ProductMapper;
import com.company.demo.service.validation.ProductValidation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProService {
    private Integer proIx;
    private final List<Product> productList;

    private final ProductValidation productValidation;
    private final ProductMapper productMapper;

    public ProService(ProductMapper productMapper, ProductValidation productValidation) {
        this.productValidation = productValidation;
        this.productMapper = productMapper;
        this.proIx = 0;
        this.productList = new ArrayList<>();
    }

    public ApiResponse<ProductDto> createProduct(ProductDto dto) {

        List<ErrorDto> errors = this.productValidation.prodPostValidation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<ProductDto>builder()
                    .code(-3)
                    .message("Validation Error!")
                    .errorList(errors)
                    .build();
        }

        Product product = this.productMapper.toEntity(dto);
        product.setId(++proIx);
        this.productList.add(product);

        return ApiResponse.<ProductDto>builder()
                .success(true)
                .message("Product is successfully created!")
                .content(this.productMapper.toDto(product))
                .build();

    }

    public ApiResponse<ProductDto> getProduct(Integer prodId) {
        for (Product product : this.productList) {
            if (product.getId().equals(prodId)) {
                return ApiResponse.<ProductDto>builder()
                        .success(true)
                        .message("OK")
                        .content(this.productMapper.toDto(product))
                        .build();
            }
        }
        return ApiResponse.<ProductDto>builder()
                .code(-1)
                .message(String.format("Product with %d id is not found!", prodId))
                .build();
    }

    public ApiResponse<ProductDto> updateProduct(Integer prodId, ProductDto newProd) {

        List<ErrorDto> errors = this.productValidation.prodPutValidation(newProd);

        if (!errors.isEmpty()) {
            return ApiResponse.<ProductDto>builder()
                    .code(-3)
                    .message("Validation Error!")
                    .errorList(errors)
                    .build();
        }

        for (Product oldProduct : this.productList) {
            if (oldProduct.getId().equals(prodId)) {
                newProd.setId(oldProduct.getId());
                int index = this.productList.indexOf(oldProduct);
                Product updateProduct = convertNewProduct(oldProduct, newProd);
                this.productList.set(index, updateProduct);
                return ApiResponse.<ProductDto>builder()
                        .success(true)
                        .message("Successfully updated!")
                        .errorList(errors)
                        .build();

            }
        }
        return ApiResponse.<ProductDto>builder()
                .code(-1)
                .message(String.format("This Product %d id is not found!",newProd.getId()))
                .build();
    }

    public String deleteProduct(Integer prodId) {
        for (Product product : this.productList) {
            if (product.getId().equals(prodId)) {
                int index = this.productList.indexOf(product);
                return String.format("User with id %d successful deleted!", product.getId());
            }
        }
        return String.format("User with %d id database is not found", prodId);
    }

    public Boolean checkName(ProductDto product) {
        for (Product product1 : this.productList) {
            if (product.getName().equals(product1.getName()))
                return false;
        }
        return true;
    }

    private Product convertNewProduct(Product oldProd, ProductDto newProd) {
        if (newProd.getName() != null) {
            oldProd.setName(newProd.getName());
        }
        if (newProd.getColour() != null) {
            oldProd.setColour(newProd.getColour());
        }
        if (newProd.getPrice() != null) {
            oldProd.setPrice(newProd.getPrice());
        }
        if (newProd.getAmount() != null) {
            oldProd.setAmount(newProd.getAmount());
        }
        return oldProd;
    }

    public List<ProductDto> getAllProductByUserId(Integer userId) {
        List<ProductDto> products = new ArrayList<>();
        for (Product product : this.productList) {
            if (product.getUserId().equals(userId)) {

                ApiResponse<ProductDto> response = new ApiResponse<>();
                response.setSuccess(true);
                response.setMessage("OK");

                products.add(this.productMapper.toDto(product));
            }
        }

        ApiResponse<ProductDto> response = new ApiResponse<>();
        response.setCode(-1);
        response.setMessage(String.format("No product related to the user with %d id was found!", userId));

        return products;
    }

    public ApiResponse<ProductDto> updateProduct() {
        return null;
    }
}


