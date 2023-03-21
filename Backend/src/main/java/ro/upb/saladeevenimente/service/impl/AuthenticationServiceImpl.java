package ro.upb.saladeevenimente.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.User;
import ro.upb.saladeevenimente.service.AuthenticationService;
import ro.upb.saladeevenimente.service.UserService;

import javax.transaction.Transactional;
import java.sql.SQLException;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public boolean register(String firstName, String lastName, String email, String password) throws SQLException {
        User user;
        user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userService.register(user);
        return true;
    }
}
