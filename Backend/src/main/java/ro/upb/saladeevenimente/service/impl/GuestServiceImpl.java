package ro.upb.saladeevenimente.service.impl;

import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.Guest;
import ro.upb.saladeevenimente.repository.GuestJdbcRepository;
import ro.upb.saladeevenimente.repository.GuestJdbcRepositoryImpl;
import ro.upb.saladeevenimente.repository.UserJdbcRepository;
import ro.upb.saladeevenimente.repository.UserJdbcRepositoryImpl;
import ro.upb.saladeevenimente.service.GuestService;

import java.sql.SQLException;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private GuestJdbcRepository guestJdbcRepository = new GuestJdbcRepositoryImpl();

    @Override
    public Guest addGuest(Guest guest) throws SQLException {
        return guestJdbcRepository.addGuest(guest);
    }

    @Override
    public void deleteGuest(Long guestId) throws SQLException {
        guestJdbcRepository.deleteGuest(guestId);
    }

    @Override
    public List<Guest> findAllGuests() throws SQLException {
        return guestJdbcRepository.getAllGuests();
    }

    @Override
    public List<Guest> getGuestsByReservationId(Long reservationId) throws SQLException {
        return guestJdbcRepository.findAllGuestsByReservationId(reservationId);
    }

    @Override
    public void updateGuest(Long guestId, Guest guest) throws SQLException {
        guestJdbcRepository.updateGuest(guestId, guest);
    }

    @Override
    public int getTheNumberOfGuests(Long reservationId) throws SQLException {
        return guestJdbcRepository.getTheNumberOfGuests(reservationId);
    }
}
