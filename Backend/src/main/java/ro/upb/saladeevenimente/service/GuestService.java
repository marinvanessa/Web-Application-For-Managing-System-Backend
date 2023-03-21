package ro.upb.saladeevenimente.service;

import ro.upb.saladeevenimente.domain.Guest;
import ro.upb.saladeevenimente.domain.Hall;

import java.sql.SQLException;
import java.util.List;

public interface GuestService {
    Guest addGuest(Guest guest) throws SQLException;
    void deleteGuest(Long guestId) throws SQLException;
    List<Guest> findAllGuests() throws SQLException;
    List<Guest> getGuestsByReservationId(Long reservationId) throws SQLException;
    void updateGuest(Long guestId, Guest guest) throws SQLException;
    int getTheNumberOfGuests(Long reservationId) throws SQLException;
}
