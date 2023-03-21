package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.Hall;
import ro.upb.saladeevenimente.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcRepositoryImpl implements  UserJdbcRepository{

    @Override
    public User save(User user) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("INSERT INTO user(first_name, last_name, email, password)" + "values (?,?,?,?)");
        c.setString(1, user.getFirstName());
        c.setString(2, user.getLastName());
        c.setString(3, user.getEmail());
        c.setString(4, user.getPassword());
        boolean resultSet = c.execute();
        return null;

    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("select * from user where email=? and password=?");
        c.setString(1, email);
        c.setString(2, password);
        ResultSet resultSet = c.executeQuery();
        User user = new User();
        while(resultSet.next()){
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    @Override
    public List<User> show() throws SQLException {
        List<User> users = new ArrayList<User>();
        User u = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("select * from user");
        ResultSet resultSet = c.executeQuery();
        while(resultSet.next()){

            u = new User(resultSet.getLong("id"),
                    resultSet.getString("email"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
            users.add(u);
        }
        return users;
    }

    @Override
    public User showUser(String email) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("select * from user where email=?");
        c.setString(1, email);
        ResultSet resultSet = c.executeQuery();
        User user = new User();
        while(resultSet.next()){
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

}
