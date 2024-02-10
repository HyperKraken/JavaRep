package com.company.demo.controller;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.UsersDto;
import com.company.demo.service.UserServiceIn;
import com.company.demo.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "users")
public class UsersController {

    private final UserServiceIn userService;

    @PostMapping
    public ApiResponse<UsersDto> creatUsers(@RequestBody @Valid UsersDto user) {
        return this.userService.createUser(user);
    }

    @GetMapping
    public ApiResponse<UsersDto> getUsers(@RequestParam(value = "id") Integer id) {
        return this.userService.getUser(id);
    }

    @PutMapping
    public ApiResponse<UsersDto> updateUsers(@RequestParam(value = "id") Integer id, @RequestBody UsersDto user) {
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping
    public ApiResponse<UsersDto> deleteUsers(@RequestParam(value = "id") Integer id) {
        return this.userService.deleteUser(id);
    }

}
