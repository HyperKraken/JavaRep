package com.company.demo.controller;

import com.company.demo.dto.ApiResponse;
import com.company.demo.dto.CardDto;
import com.company.demo.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "card")

public class CardController {
    private final CardService cardService;

    @PostMapping
    public ApiResponse<CardDto> createCard(@RequestBody @Valid CardDto dto){
        return this.cardService.createCard(dto);
    }

    @GetMapping
    public ApiResponse<CardDto> getCardById(@RequestParam(value = "id") Integer cardId){
        return this.cardService.getCard(cardId);
    }

    @PutMapping
    public ApiResponse<CardDto> updateCard(@RequestParam Integer cardId, @RequestBody @Valid CardDto dto){
        return this.cardService.updateCard(cardId,dto);
    }

    @DeleteMapping
    public ApiResponse<CardDto> deleteCard(@RequestParam(value = "id") Integer cardId){
        return this.cardService.deleteCard(cardId);
    }


}
