package ro.upb.saladeevenimente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.upb.saladeevenimente.domain.Guest;
import ro.upb.saladeevenimente.domain.Hall;
import ro.upb.saladeevenimente.domain.User;
import ro.upb.saladeevenimente.service.GuestService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("")
    public Guest addGuest(@RequestBody Guest guest) throws SQLException {
        return guestService.addGuest(guest);
    }

    @CrossOrigin("http://localhost:3000")
    @DeleteMapping("/delete/{guestId}")
    public void deleteGuest(@PathVariable Long guestId) throws SQLException {
        guestService.deleteGuest(guestId);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/all")
    public List<Guest> show() throws SQLException {
        return guestService.findAllGuests();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/{reservationId}")
    public List<Guest> getGuestsByReservationId(@PathVariable Long reservationId) throws SQLException {
        return guestService.getGuestsByReservationId(reservationId);
    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("/{guestId}")
    public void updateGuest(@PathVariable Long guestId, @RequestBody Guest guest) throws SQLException {
        guestService.updateGuest(guestId, guest);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/noGuests/{reservationId}")
    public int getTheNumberOfGuests(@PathVariable Long reservationId) throws SQLException {
        return guestService.getTheNumberOfGuests(reservationId);
    }
}
