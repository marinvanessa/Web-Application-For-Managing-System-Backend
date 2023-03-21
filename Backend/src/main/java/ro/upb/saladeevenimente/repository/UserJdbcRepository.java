package ro.upb.saladeevenimente.repository;


import ro.upb.saladeevenimente.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserJdbcRepository {
    User save(User user) throws SQLException;
    User findUserByEmailAndPassword(String email, String password) throws SQLException;
    List<User> show() throws SQLException;
    User showUser(String email) throws SQLException;
}
