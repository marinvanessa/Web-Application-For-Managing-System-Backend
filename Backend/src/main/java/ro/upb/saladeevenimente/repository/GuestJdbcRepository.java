package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.Guest;

import java.sql.SQLException;
import java.util.List;

public interface GuestJdbcRepository {
    Guest addGuest(Guest guest) throws SQLException;
    void deleteGuest(Long guestId) throws SQLException;
    List<Guest> getAllGuests() throws SQLException;
    List<Guest> findAllGuestsByReservationId(Long reservationId) throws SQLException;
    void updateGuest(Long guestId, Guest guest) throws SQLException;
    int getTheNumberOfGuests(Long reservationId) throws SQLException;
}
