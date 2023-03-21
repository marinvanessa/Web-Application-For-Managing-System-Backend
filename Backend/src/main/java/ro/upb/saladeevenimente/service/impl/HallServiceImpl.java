package ro.upb.saladeevenimente.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.Hall;
import ro.upb.saladeevenimente.repository.HallJdbcRepository;
import ro.upb.saladeevenimente.repository.HallJdbcRepositoryImpl;
import ro.upb.saladeevenimente.service.HallService;

import java.sql.SQLException;
import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    private HallJdbcRepository hallJdbcRepository =  new HallJdbcRepositoryImpl();

    @Override
    public Hall addHall(Hall hall) throws SQLException {
        return hallJdbcRepository.save(hall);
    }

    @Override
    public void deleteHall(Long id) throws SQLException {
        hallJdbcRepository.deleteById(id);
    }

    @Override
    public void updateHall(Long id, Hall hall) throws SQLException {
        hallJdbcRepository.update(id, hall);
    }

    @Override
    public List<Hall> findAllHalls() throws SQLException {
        return hallJdbcRepository.show();
    }

    @Override
    public List<Hall> getAllHallsWithReservations() throws SQLException {
        return hallJdbcRepository.getAllHallsWithReservations();
    }


}
