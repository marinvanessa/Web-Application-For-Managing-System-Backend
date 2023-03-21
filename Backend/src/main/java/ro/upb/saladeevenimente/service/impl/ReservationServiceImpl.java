package ro.upb.saladeevenimente.service.impl;

import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.Reservation;
import ro.upb.saladeevenimente.repository.HallJdbcRepository;
import ro.upb.saladeevenimente.repository.HallJdbcRepositoryImpl;
import ro.upb.saladeevenimente.repository.ReservationJdbcRepository;
import ro.upb.saladeevenimente.repository.ReservationJdbcRepositoryImpl;
import ro.upb.saladeevenimente.service.ReservationService;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationJdbcRepository reservationJdbcRepository =  new ReservationJdbcRepositoryImpl();

    @Override
    public Reservation addReservation(Reservation reservation) throws SQLException {
        return reservationJdbcRepository.addReservation(reservation);
    }

    @Override
    public void deleteReservation(Long reservationId) throws SQLException {
        reservationJdbcRepository.deleteReservation(reservationId);
    }

    @Override
    public List<Reservation> getAllReservationsByUserId(Long userId) throws SQLException {
        return reservationJdbcRepository.findAllReservationsByUserId(userId);
    }

    @Override
    public void confirmReservation(Long reservationId, Reservation reservation) throws SQLException {
        reservationJdbcRepository.confirmReservation(reservationId, reservation);
    }

    @Override
    public void editReservation(Long reservationId, Reservation reservation) throws SQLException {
        reservationJdbcRepository.editReservation(reservationId, reservation);
    }

    @Override
    public List<Reservation> findAllConfirmedReservations(Boolean value) throws SQLException {
        return reservationJdbcRepository.getAllConfirmedReservations(value);
    }

    @Override
    public List<Reservation> findAllReservationsWithoutGuests() throws SQLException {
        return reservationJdbcRepository.getAllReservationsWithoutGuests();
    }

    @Override
    public List<String> findReservationByHallDimension() throws SQLException {
        return reservationJdbcRepository.getReservationByHallDimension();
    }

    @Override
    public List<String> findCheapReservations() throws SQLException {
        return reservationJdbcRepository.getCheapReservations();
    }

    @Override
    public List<String> findExpensiveReservations() throws SQLException {
        return reservationJdbcRepository.getExpensiveReservations();
    }


}
