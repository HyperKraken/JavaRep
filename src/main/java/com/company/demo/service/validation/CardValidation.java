package com.company.demo.service.validation;

import com.company.demo.dto.CardDto;
import com.company.demo.dto.ErrorDto;
import com.company.demo.repository.CardRepository;
import com.company.demo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardValidation {
    private final CardRepository cardRepository;
    private final UsersRepository usersRepository;

    public List<ErrorDto> cardValid(CardDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (this.cardRepository.existsByCardNumberAndDeletedAtIsNull(dto.getCardNumber())) {
            errors.add(new ErrorDto("Card Number", String.format("%s cardNumber database already exists!", dto.getCardNumber())));
        }
        if (!this.usersRepository.existsById(dto.getUserId())) {
            errors.add(new ErrorDto("UserId", String.format("User with %d id is not found", dto.getUserId())));
        }
        return errors;
    }

    public List<ErrorDto> cardPutValidation(CardDto dto) {
        return null;
    }
}
