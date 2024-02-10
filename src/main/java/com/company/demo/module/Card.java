package com.company.demo.module;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "card")

public class Card{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    private String cardHolder;

    @NotBlank(message = "Card name cannot be empty!")
    private String cardName;
    @NotBlank(message = "Card number cannot be empty!")
    private String cardNumber;
    @NotBlank(message = "Card message cannot be empty!")
    private String cardCode;
    @NotBlank(message = "UserId cannot be empty!")
    private Integer userId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId",
            insertable = false, updatable = false
    )
    private Users user;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
