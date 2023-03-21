package ro.upb.saladeevenimente.service.impl;

import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.User;
import ro.upb.saladeevenimente.repository.UserJdbcRepository;
import ro.upb.saladeevenimente.repository.UserJdbcRepositoryImpl;
import ro.upb.saladeevenimente.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserJdbcRepository userJdbcRepository = new UserJdbcRepositoryImpl();

    @Override
    public User register(User user) throws SQLException {
        return userJdbcRepository.save(user);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        return userJdbcRepository.findUserByEmailAndPassword(email, password);
    }

    @Override
    public List<User> findAllUsers() throws SQLException {
        return userJdbcRepository.show();
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        return userJdbcRepository.showUser(email);
    }


}
