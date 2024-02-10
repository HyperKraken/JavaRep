package com.company.demo.service.impl;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.CardDto;
import com.company.demo.dto.ErrorDto;
import com.company.demo.module.Card;
import com.company.demo.repository.CardRepository;
import com.company.demo.service.CardService;
import com.company.demo.service.exceptions.ContentNotFoundException;
import com.company.demo.service.exceptions.DatabaseException;
import com.company.demo.service.mapper.CardMapper;
import com.company.demo.service.validation.CardValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;
    private final CardValidation cardValidation;
    private final CardRepository cardRepository;
    private final ObjectMapper objectMapper;



    @Override
    public ApiResponse<CardDto> createCard(CardDto dto) {
        try {
            Card card = this.cardMapper.toEntity(dto);

            return ApiResponse.<CardDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.cardMapper.toDto(this.cardRepository.save(card)))
                    .build();
        } catch (Exception e) {
        throw new DatabaseException(String.format("Card while saving error!,Message %s",e.getMessage()));
        }
    }


    @Override
    public ApiResponse<CardDto> getCard(Integer cardId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(cardId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("Card with %d id is not found!", cardId));
        }
        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ApiResponse<CardDto> updateCard(Integer cardId, CardDto dto) {
        List<ErrorDto> errors = this.cardValidation.cardPutValidation(dto);
        if (errors.isEmpty()) {
            throw new ContentNotFoundException("Validation error!");
        }

        return ApiResponse.<CardDto>builder()
                .success(true)
                .message("OK")
                .content(this.cardMapper.toDto(this.cardRepository.save(this.cardMapper.toEntity(dto))))
                .build();
    }

    @Override
    public ApiResponse<CardDto> deleteCard(Integer cardId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(cardId);
        if (optional.isEmpty()) {
            throw new ContentNotFoundException(String.format("Card with %d id is not found", cardId));
        }

        Card card = optional.get();
        card.setDeletedAt(LocalDateTime.now());
        return ApiResponse.<CardDto>builder()
                .success(true)
                .message(String.format("Card %d id successful deleted", cardId))
                .content(this.cardMapper.toDto(this.cardRepository.save(card)))
                .build();
    }
}
