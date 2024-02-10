package com.company.demo.service.validation;

import com.company.demo.dto.ErrorDto;
import com.company.demo.dto.UsersDto;
import com.company.demo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UsersRepository usersRepository;

    public List<ErrorDto> userPostValidation(UsersDto dto) {
        List<ErrorDto> errors = new ArrayList<>();

        if (this.usersRepository.existsByEmailAndDeletedAtIsNull(dto.getEmail())) {
            errors.add(new ErrorDto("email", String.format("This email %s already exists!", dto.getEmail())));
        }
        return errors;
    }

    public List<ErrorDto> userPutValidation(UsersDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (dto.getEmail() != null) {
            if (this.usersRepository.existsByEmailAndDeletedAtIsNull(dto.getEmail())) {
                errors.add(new ErrorDto("email", String.format("%s email already exists!", dto.getEmail())));
            }
        }
        return errors;
    }
}
