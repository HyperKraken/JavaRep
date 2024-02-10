package com.company.demo.repository;

import com.company.demo.module.Card;
import com.company.demo.module.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Users> findByUserIdAndDeletedAtIsNull(Integer cardId);

    Optional<Card> findByCardIdAndDeletedAtIsNull(Integer cardId);

    List<Card> findAllByDeletedAtIsNull();

    Boolean existsByCardNumberAndDeletedAtIsNull(String cardNumber);
}
