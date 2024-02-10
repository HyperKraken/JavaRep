package com.company.demo.service.impl;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.ErrorDto;
import com.company.demo.dto.UsersDto;
import com.company.demo.module.Users;
import com.company.demo.repository.UsersRepository;
import com.company.demo.service.UserServiceIn;
import com.company.demo.service.exceptions.ContentNotFoundException;
import com.company.demo.service.mapper.UserMapper;
import com.company.demo.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceIn {

    private Integer userIx;
    private final UserMapper userMapper;

    private final UsersRepository usersRepository;

    private final UserValidation userValidation;


    @Override
    public ApiResponse<UsersDto> createUser(@RequestBody UsersDto dto) {

        List<ErrorDto> errors = this.userValidation.userPostValidation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<UsersDto>builder()
                    .code(-3)
                    .message("Validation Error!")
                    .errorList(errors)
                    .build();
        }

        Users users = this.userMapper.toEntity(dto);

        ApiResponse<UsersDto> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("OK");
        response.setContent(this.userMapper.toDto(this.usersRepository.save(users)));
        return response;
    }

    @Override
    public ApiResponse<UsersDto> getUser(@RequestParam Integer userId) {

        Optional<Users> optional = this.usersRepository.findByUserIdAndDeletedAtIsNull(userId);

        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("User with %d id is not found!", userId));
        }
        return ApiResponse.<UsersDto>builder()
                .success(true)
                .message("OK")
                .content(this.userMapper.toDtoWithCard(optional.get()))
                .build();
    }

    @Override
    public ApiResponse<UsersDto> updateUser(@RequestParam Integer userId, @RequestBody UsersDto dto) {

        List<ErrorDto> errors = this.userValidation.userPostValidation(dto);
        if (!errors.isEmpty()) {
            return ApiResponse.<UsersDto>builder()
                    .code(-3)
                    .message("Validation Error!")
                    .errorList(errors)
                    .build();
        }

        Optional<Users> optional = this.usersRepository.findByUserIdAndDeletedAtIsNull(userId);

        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("User with %d id is not found!", userId));
        }

        try {

            Users updatedUser = this.userMapper.convertNewUsers(optional.get(), dto);
            updatedUser.setUpdatedAt(LocalDateTime.now());

            ApiResponse<UsersDto> response = new ApiResponse<>();
            response.setSuccess(true);
            response.setMessage(String.format("User with %d id successfully updated!", updatedUser.getUserId()));
            response.setContent(this.userMapper.toDto(this.usersRepository.save(updatedUser)));
            return response;

        } catch (Exception e) {
            return ApiResponse.<UsersDto>builder()
                    .code(-2)
                    .message(String.format("User while updating error! Message %s", e.getMessage()))
                    .build();
        }    }

    @Override
    public ApiResponse<UsersDto> deleteUser(@RequestParam Integer userId) {

        Optional<Users> optional = this.usersRepository.findByUserIdAndDeletedAtIsNull(userId);

        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("User with %d id is not found!", userId));
        }

        Users users = optional.get();
        users.setDeletedAt(LocalDateTime.now());

        ApiResponse<UsersDto> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage(String.format("User with %d id successfully deleted!", users.getUserId()));
        response.setContent(this.userMapper.toDto(this.usersRepository.save(users)));

        return response;
    }
}
