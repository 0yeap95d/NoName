package com.example.demo.dto.user;

import com.example.demo.entity.user.UserEntity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "user")
@NoArgsConstructor
public class UserJoinDto {

    private Long id;
    private String email;
    private String password;
    private String nickname;

    @Builder
    public UserJoinDto(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public UserEntity toEntity() {
        return UserEntity.builder().id(id).email(email).password(password).nickname(nickname).build();
    }
}
