package com.company.demo.service;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.UsersDto;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceIn {
    ApiResponse<UsersDto> createUser(UsersDto dto);
    ApiResponse<UsersDto> getUser(Integer userId);
    ApiResponse<UsersDto> updateUser(Integer userId, UsersDto dto);
    ApiResponse<UsersDto> deleteUser(Integer userId);
}
