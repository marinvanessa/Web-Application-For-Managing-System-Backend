package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.Reservation;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;


public interface ReservationJdbcRepository {
    Reservation addReservation(Reservation reservation) throws SQLException;
    void deleteReservation(Long reservationId) throws SQLException;
    List<Reservation> findAllReservationsByUserId(Long userId) throws SQLException;
    void confirmReservation(Long reservationId, Reservation reservation) throws SQLException;
    void editReservation(Long reservationId, Reservation reservation) throws SQLException;
    List<Reservation> getAllConfirmedReservations(Boolean value) throws SQLException;
    List<Reservation> getAllReservationsWithoutGuests() throws SQLException;
    List<String> getReservationByHallDimension() throws SQLException;
    List<String> getCheapReservations() throws SQLException;
    List<String> getExpensiveReservations() throws SQLException;

}
