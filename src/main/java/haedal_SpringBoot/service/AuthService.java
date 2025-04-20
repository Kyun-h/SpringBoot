package haedal_SpringBoot.service;

import haedal_SpringBoot.domain.User;
import haedal_SpringBoot.dto.LoginRequestDto;
import haedal_SpringBoot.dto.UserSimpleResponseDto;
import haedal_SpringBoot.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserService userService;
  private final UserRepository userRepository;

  @Autowired
  public AuthService(UserService userService, UserRepository userRepository){
    this.userService = userService;
    this.userRepository = userRepository;
  }
  //login(loginRequestDto,request)
  public UserSimpleResponseDto login(LoginRequestDto loginRequestDto, HttpServletRequest request){
    User user = userRepository.findByUsername(loginRequestDto.getUsername())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    if(!loginRequestDto.getPassword().equals(user.getPassword())){
      throw new IllegalArgumentException("잘못된 비밀번호입니다.");
    }
    HttpSession session = request.getSession();
    session.setAttribute("user",user);

    return userService.convertUserToSimpleDto(user, user);
  }

  public void logout(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    if(session != null){
      session.invalidate();
    }
  }

  public User getCurrentUser(HttpServletRequest request){
    HttpSession session = request.getSession(false);
    if(session == null || session.getAttribute("user") == null){
      throw new IllegalArgumentException("로그인 되지 않았습니다.");
    }
    return (User) session.getAttribute("user");
  }

}
