package com.company.demo.service;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.CardDto;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    ApiResponse<CardDto> createCard(CardDto dto);

    ApiResponse<CardDto> getCard(Integer cardId);


    ApiResponse<CardDto> updateCard(Integer cardId, CardDto dto);

    ApiResponse<CardDto> deleteCard(Integer cardId);

}
