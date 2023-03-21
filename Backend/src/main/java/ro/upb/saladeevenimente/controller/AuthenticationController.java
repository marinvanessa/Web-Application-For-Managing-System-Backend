package ro.upb.saladeevenimente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.upb.saladeevenimente.domain.User;
import ro.upb.saladeevenimente.dto.LoginDto;
import ro.upb.saladeevenimente.dto.RegisterDto;
import ro.upb.saladeevenimente.service.AuthenticationService;
import ro.upb.saladeevenimente.service.UserService;

import java.sql.SQLException;


@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerRequestDto) throws SQLException {
        boolean registered = authenticationService.register(
                registerRequestDto.getFirstName(),
                registerRequestDto.getLastName(),
                registerRequestDto.getEmail(),
                registerRequestDto.getPassword());
        if (registered) {
            return ResponseEntity.ok("registered");
        }
        return  ResponseEntity.ok("not registered");

    }
    @CrossOrigin("http://localhost:3000")
    @PostMapping("/user/login")
    public User login(@RequestBody LoginDto loginDto) throws SQLException {
        User user = userService.getUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        if (user.getEmail().matches(loginDto.getEmail())
                && user.getPassword().matches(loginDto.getPassword())) {
            return user;
        }
        else
            return null;
    }

}
