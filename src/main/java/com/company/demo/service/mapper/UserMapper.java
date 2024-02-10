package com.company.demo.service.mapper;

import com.company.demo.dto.UsersDto;
import com.company.demo.module.Users;
import com.company.demo.repository.CardRepository;
import com.company.demo.service.CardService;
import com.company.demo.service.ProService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    private final ProService proService;

    public Users toEntity(UsersDto dto) {
        Users users = new Users();
        users.setFirstname(dto.getFirstname());
        users.setLastname(dto.getLastname());
        users.setEmail(dto.getEmail());
        users.setPassword(dto.getPassword());
        return users;
    }

    public UsersDto toDto(Users users) {
        UsersDto dto = new UsersDto();
        dto.setUserId(users.getUserId());
        dto.setFirstname(users.getFirstname());
        dto.setLastname(users.getLastname());
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        return dto;
    }

    public UsersDto toDtoWithProduct(Users users) {
        UsersDto dto = new UsersDto();
        dto.setUserId(users.getUserId());
        dto.setFirstname(users.getFirstname());
        dto.setLastname(users.getLastname());
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        dto.setProducts(this.proService.getAllProductByUserId(users.getUserId()));
        return dto;
    }


    public Users convertNewUsers(Users users, UsersDto dto) {
        if (dto.getFirstname() != null) {
            users.setFirstname(dto.getFirstname());
        }
        if (dto.getLastname() != null) {
            users.setLastname(dto.getLastname());
        }
        if (dto.getEmail() != null) {
            users.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            users.setPassword(dto.getPassword());
        }
        return users;

    }

    public UsersDto toDtoWithCard(Users users) {
        return UsersDto.builder()
                .userId(users.getUserId())
                .firstname(users.getFirstname())
                .email(users.getEmail())
                .password(users.getPassword())
                .cards(this.cardMapper.convertToDtoCard(users.getCards()))
//                .cards(this.cardMapper.convertToDtoCard(this.cardRepository.findAllByUserIdAndDeletedAtIsNull(user.getUserId())))
                .build();
    }
}
