package haedal_SpringBoot.controller;

import haedal_SpringBoot.domain.User;
import haedal_SpringBoot.dto.UserRegistrationRequestDto;
import haedal_SpringBoot.dto.UserSimpleResponseDto;
import haedal_SpringBoot.service.UserService;
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

}
