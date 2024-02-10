package com.company.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private Integer userId;
    @NotBlank(message = "Firstname cannot be null or empty!")
    private String firstname;
    @NotBlank(message = "Lastname cannot be null or empty!")
    private String lastname;
    @Email
    @NotBlank(message = "Email cannot be null or empty!")
    private String email;
    @Size(min = 4,max = 10,message = "Password length must be from 4 to 10!")
    @NotBlank(message = "Password cannot be null or empty!")
    private String password;

    private List<String> phoneNumbers;

    private List<ProductDto> products;

    private List<CardDto> cards;
}
