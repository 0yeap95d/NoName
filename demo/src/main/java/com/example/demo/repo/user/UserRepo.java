package com.example.demo.repo.user;

import com.example.demo.entity.user.UserEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
}
