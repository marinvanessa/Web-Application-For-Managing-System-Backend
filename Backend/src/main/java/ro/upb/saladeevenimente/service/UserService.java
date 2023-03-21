package ro.upb.saladeevenimente.service;


import ro.upb.saladeevenimente.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User register(User user) throws SQLException;
    User getUserByEmailAndPassword(String email, String password) throws SQLException;
    List<User> findAllUsers() throws SQLException;
    User getUserByEmail(String email) throws SQLException;

}
