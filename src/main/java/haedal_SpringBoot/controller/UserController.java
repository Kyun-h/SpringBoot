package haedal_SpringBoot.controller;

import haedal_SpringBoot.domain.User;
import haedal_SpringBoot.dto.UserDetailResponseDto;
import haedal_SpringBoot.dto.UserRegistrationRequestDto;
import haedal_SpringBoot.dto.UserSimpleResponseDto;
import haedal_SpringBoot.dto.UserUpdateRequestDto;
import haedal_SpringBoot.service.AuthService;
import haedal_SpringBoot.service.UserService;
//패키지명이 다를 시 본인 패키지명으로 작성해야 오류가 안납니다.

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
  private final UserService userService;
  private final AuthService authService;

  @Autowired
  public UserController(UserService userService, AuthService authService){
    this.userService = userService;
    this.authService = authService; // authService도 추가
  }

  @GetMapping("/users") // 유저 정보 간략 조회
  public ResponseEntity<List<UserSimpleResponseDto>> getUsers(@RequestParam(required = false)String username, HttpServletRequest request) {
    User currentUser = authService.getCurrentUser(request);

    List<UserSimpleResponseDto> users;
    if (username == null || username.isEmpty()) {
      users = userService.getAllUsers(currentUser);
    } else {
      users = userService.getUserByUsername(currentUser, username);
    }

    return ResponseEntity.ok(users);
  }

  @PutMapping("/users/profile") // 유저 정보 수정 (이미지 제외)
  public ResponseEntity<UserDetailResponseDto> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto, HttpServletRequest request) {
    User currentUser = authService.getCurrentUser(request);
    UserDetailResponseDto updated = userService.updateUser(currentUser, userUpdateRequestDto);
    return ResponseEntity.ok(updated);
  }

  @GetMapping("/users/{userId}/profile") // 유저 정보 상세 조회
  public ResponseEntity<UserDetailResponseDto>
  getUserProfile(@PathVariable Long userId, HttpServletRequest request) {
    User currentUser = authService.getCurrentUser(request);

    UserDetailResponseDto userDetailResponseDto = userService.getUserDetail(currentUser, userId);

    return ResponseEntity.ok(userDetailResponseDto);
  }
}