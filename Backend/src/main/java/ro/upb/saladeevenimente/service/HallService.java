package ro.upb.saladeevenimente.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import ro.upb.saladeevenimente.domain.Hall;

import java.sql.SQLException;
import java.util.List;

public interface HallService {
    Hall addHall(Hall hall) throws SQLException;
    void deleteHall(Long id) throws SQLException;
    void updateHall(Long id, Hall hall) throws SQLException;
    List<Hall> findAllHalls() throws SQLException;
    List<Hall> getAllHallsWithReservations() throws SQLException;


}
