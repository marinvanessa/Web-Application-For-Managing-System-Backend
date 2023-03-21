package ro.upb.saladeevenimente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.upb.saladeevenimente.domain.User;
import ro.upb.saladeevenimente.service.UserService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/all")
    List<User> show() throws SQLException {
        return userService.findAllUsers();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable("email") String email) throws SQLException {
        return userService.getUserByEmail(email);
    }

}
