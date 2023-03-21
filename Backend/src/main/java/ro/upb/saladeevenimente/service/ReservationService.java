package ro.upb.saladeevenimente.service;

import ro.upb.saladeevenimente.domain.Reservation;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public interface ReservationService {
    Reservation addReservation(Reservation reservation) throws SQLException;
    void deleteReservation(Long reservationId) throws SQLException;
    List<Reservation> getAllReservationsByUserId(Long userId) throws SQLException;
    void confirmReservation(Long reservationId, Reservation reservation) throws SQLException;
    void editReservation(Long reservationId, Reservation reservation) throws SQLException;
    List<Reservation> findAllConfirmedReservations(Boolean value) throws SQLException;
    List<Reservation> findAllReservationsWithoutGuests() throws SQLException;
    List<String> findReservationByHallDimension() throws SQLException;
    List<String> findCheapReservations() throws SQLException;
    List<String> findExpensiveReservations() throws SQLException;
}
