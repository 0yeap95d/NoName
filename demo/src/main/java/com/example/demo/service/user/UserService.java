package com.example.demo.service.user;

import com.example.demo.entity.user.UserEntity;
import com.example.demo.repo.user.UserRepo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

    // @Transactional
    // public Long save(UserRequestEntity userRequestEntity) {
    // return userRepo.save(userRequestEntity.toRedisHash()).getNo();
    // }

    // public UserResponseEntity get(Long no) {
    // UserDto userDto = userRepo.findById(no)
    // .orElseThrow(() -> new IllegalArgumentException("Nothing saved. id=" + no));
    // return new UserResponseEntity(userDto);
    // }

    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void delete() {
        userRepo.deleteAll();
    }

}
