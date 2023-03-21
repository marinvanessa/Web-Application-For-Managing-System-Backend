package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.SheetUserWorker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SheetUserWorkerJdbcRepositoryImpl implements SheetUserWorkerJdbcRepository{

    @Override
    public SheetUserWorker addSheetUserWorker(Long userId) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "INSERT INTO sheet_user_worker(user_id) values((SELECT id FROM user where id = ?))");
        c.setLong(1, userId);
        c.executeUpdate();
        return null;
    }
}
