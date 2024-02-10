package com.company.demo.controller;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.ProductDto;
import com.company.demo.service.ProService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "Product")
public class ProdController {
    private final ProService proService;

    public ProdController(ProService proService){
        this.proService = proService;
    }

    @PostMapping
    public ApiResponse<ProductDto> createProduct(@RequestBody() ProductDto dto){
        return this.proService.createProduct(dto);
    }

    @GetMapping()
    public ApiResponse<ProductDto> getProduct(@PathVariable(value = "id") Integer prodId){
        return this.proService.getProduct(prodId);
    }

    @PutMapping
    public ApiResponse<ProductDto> updateProduct(@RequestParam(value = "id") Integer prodId, @RequestBody ProductDto product){
        return this.proService.updateProduct(prodId,product);
    }

    @DeleteMapping
    public String deleteProduct(@RequestParam(value = "id") Integer prodId){
        return this.proService.deleteProduct(prodId);
    }

}
