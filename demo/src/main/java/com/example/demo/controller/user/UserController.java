package com.example.demo.controller.user;

import com.example.demo.entity.user.UserEntity;
import com.example.demo.service.user.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // @ApiOperation(value = "User 회원가입")
    // @PostMapping("/join")
    // public void join(@RequestBody UserJoinDto UserJoinDto) {
    // log.info("User Controller Join");
    // userService.insert(UserJoinDto);
    // }

    // @ApiOperation(value = "User 조회")
    // @GetMapping("/get/{no}")
    // public UserResponseEntity get(@PathVariable Long no) {
    // return userService.get(no);
    // }

    @ApiOperation(value = "Email로 유저 검색")
    @GetMapping("/find")
    public UserEntity findByEmail(String email) {
        return userService.findByEmail(email);
    }

    @ApiOperation(value = "User 전체 삭제")
    @DeleteMapping("/delete")
    public void delete() {
        log.info("User Controller Delete All");
        userService.delete();
    }
}