package ro.upb.saladeevenimente.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.upb.saladeevenimente.domain.Hall;

import java.sql.SQLException;
import java.util.List;

public interface HallJdbcRepository {
    Hall save(Hall hall) throws SQLException;
    void deleteById(Long id) throws SQLException;
    void update(Long id, Hall hall) throws SQLException;
    List<Hall> show() throws SQLException;
    List<Hall> getAllHallsWithReservations() throws SQLException;
}
