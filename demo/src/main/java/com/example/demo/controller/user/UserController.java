package com.example.demo.controller.user;

import java.util.List;

import com.example.demo.entity.user.UserEntity;
import com.example.demo.service.user.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "모든 유저 검색")
    @GetMapping("/find/all")
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @ApiOperation(value = "ID로 유저 검색")
    @GetMapping("/find/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity userEntity = userService.findById(id);
        return ResponseEntity.ok().body(userEntity);
    }

    @ApiOperation(value = "유저 추가")
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity) {
        userService.save(userEntity);
        return ResponseEntity.ok().body(userEntity);
    }

    @ApiOperation(value = "유저 수정")
    @PutMapping("/update")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity) {
        userService.save(userEntity);
        return ResponseEntity.ok().body(userEntity);
    }

    @ApiOperation(value = "유저 삭제")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @ApiOperation(value = "유저 전체 삭제")
    @DeleteMapping("/delete")
    public void deleteAll() {
        userService.deleteAll();
    }
}