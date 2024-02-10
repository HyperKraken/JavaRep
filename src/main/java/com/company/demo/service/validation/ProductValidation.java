package com.company.demo.service.validation;

import com.company.demo.dto.ErrorDto;
import com.company.demo.dto.ProductDto;
import com.company.demo.service.ProService;
import com.company.demo.service.impl.UserServiceImpl;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidation {
    @Autowired
    private UserServiceImpl userService;
    @Lazy
    @Autowired
    private ProService proService;

    public List<ErrorDto> prodPostValidation(ProductDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (StringUtils.isBlank(dto.getName())) {
            errors.add(new ErrorDto("name", "Name cannot be null or empty"));
        }
        if (dto.getPrice() == null) {
            errors.add(new ErrorDto("price", "Price cannot be null or empty"));
        }
        if (dto.getAmount() == null) {
            errors.add(new ErrorDto("amount", "Amount cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getColour())) {
            errors.add(new ErrorDto("colour", "Colour cannot be null or empty"));
        }
        if (dto.getUserId() == null) {
            errors.add(new ErrorDto("userId", "UserId cannot be null"));
        }
        if (dto.getUserId() != null) {
            if (this.userService.getUser(dto.getUserId()).getContent() == null) {
                errors.add(new ErrorDto("userId", String.format("User with %d id is not found!", dto.getUserId())));
            }
        }
        return errors;
    }

    public List<ErrorDto> prodPutValidation(ProductDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (dto.getName() != null) {
            if (this.proService.checkName(dto)) {
                errors.add(new ErrorDto("Product name", String.format("%s product name already exists!", dto.getName())));
            }
        }

        return errors;
    }
}
