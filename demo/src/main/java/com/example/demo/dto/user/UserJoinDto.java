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

    private Long no;
    private String email;
    private String password;
    private String nickname;

    @Builder
    public UserJoinDto(Long no, String email, String password, String nickname) {
        this.no = no;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public UserEntity toEntity() {
        return UserEntity.builder().no(no).email(email).password(password).nickname(nickname).build();
    }
}
