package com.example.demo.entity.user;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

@Getter
@Setter
@Document(collection = "user")
public class UserEntity {

    @Id
    private Long id;
    private String email;
    private String password;
    private String nickname;

    @Builder
    public UserEntity(Long id, String email, String password, String nickname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
