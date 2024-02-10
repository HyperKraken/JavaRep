package com.company.demo.service.exceptions;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse<List<ErrorDto>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorDto> errorsList = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String rejectionValue = String.valueOf(fieldError.getRejectedValue());
            String detailMessage = fieldError.getDefaultMessage();
            errorsList.add(new ErrorDto(fieldName, String.format("Message %s, Rejection value %s", detailMessage, rejectionValue)));

        }
        return ApiResponse.<List<ErrorDto>>builder()
                .code(-3)
                .message("Validation Error!")
                .errorList(errorsList)
                .build();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ContentNotFoundException.class)
    public ApiResponse<Void> contentNotFoundException(ContentNotFoundException e) {
        return ApiResponse.<Void>builder()
                .code(-1)
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DatabaseException.class)
    public ApiResponse<Void> DatabaseException(DatabaseException e) {
        return ApiResponse.<Void>builder()
                .code(-2)
                .message(e.getMessage())
                .build();
    }
}

