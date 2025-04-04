package HaedalSpringBoot.First_project.controller;

import HaedalSpringBoot.First_project.domain.User;
import HaedalSpringBoot.First_project.dto.UserRegistrationRequestDto;
import HaedalSpringBoot.First_project.dto.UserSimpleResponseDto;
import HaedalSpringBoot.First_project.service.UserService;
//패키지명이 다를 시 본인 패키지명으로 작성해야 오류가 안납니다.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/auth/register")
  public ResponseEntity<UserSimpleResponseDto> registerUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
    User user = new User(
        userRegistrationRequestDto.getUsername(),
        userRegistrationRequestDto.getPassword(),
        userRegistrationRequestDto.getName()
    );
    UserSimpleResponseDto savedUser = userService.saveUser(user);

    return ResponseEntity.ok(savedUser);
  }
}