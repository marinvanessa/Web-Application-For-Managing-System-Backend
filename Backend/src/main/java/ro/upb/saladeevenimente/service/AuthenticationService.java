package ro.upb.saladeevenimente.service;

import ro.upb.saladeevenimente.domain.User;

import java.sql.SQLException;

public interface AuthenticationService {
    boolean register(String firstName, String lastName, String email, String password) throws SQLException;
//    User login(String email, String password);
}
