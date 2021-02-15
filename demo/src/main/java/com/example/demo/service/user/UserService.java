package com.example.demo.service.user;

import java.util.List;

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

    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }

    public UserEntity findById(Long id) {
        UserEntity userEntity = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nothing saved. id= " + id));
        return userEntity;
    }

    public void save(UserEntity userEntity) {
        userRepo.save(userEntity);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }

}
