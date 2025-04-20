package haedal_SpringBoot.dto;

import lombok.Getter;

@Getter
public class UserRegistrationRequestDto {
  private String username;
  private String password;
  private String name;
}
