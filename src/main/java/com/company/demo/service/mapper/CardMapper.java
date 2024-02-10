package com.company.demo.service.mapper;

import com.company.demo.dto.CardDto;
import com.company.demo.module.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardMapper {
    public Card toEntity(CardDto dto) {
        return Card.builder()
                .cardHolder(dto.getCardHolder())
                .cardName(dto.getCardName())
                .cardCode(dto.getCardCode())
                .userId(dto.getUserId())
                .build();
    }

    public CardDto toDto(Card card){
        return CardDto.builder()
                .userId(card.getUserId())
                .cardId(card.getCardId())
                .cardHolder(card.getCardHolder())
                .cardName(card.getCardName())
                .cardNumber(card.getCardNumber())
                .cardCode(card.getCardCode())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .deletedAt(card.getDeletedAt())
                .build();
    }

    public List<CardDto> convertToDtoCard(List<Card> cards) {
        List<CardDto> cardDto = new ArrayList<>();
        for (Card card : cards) {
            cardDto.add(this.toDto(card));
        }
        return cardDto;
    }
}
