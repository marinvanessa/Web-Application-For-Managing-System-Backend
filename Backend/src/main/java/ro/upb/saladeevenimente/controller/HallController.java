package ro.upb.saladeevenimente.controller;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.upb.saladeevenimente.domain.Hall;
import ro.upb.saladeevenimente.service.HallService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("")
    public Hall addHall(@RequestBody Hall hall) throws SQLException {
        return hallService.addHall(hall);
    }

    @CrossOrigin("http://localhost:3000")
    @DeleteMapping("/delete/{hallId}")
    public void deleteHall(@PathVariable Long hallId) throws SQLException {
        hallService.deleteHall(hallId);
    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("/{id}")
    public void updateHall(@PathVariable Long id, @RequestBody Hall hall) throws SQLException {
        hallService.updateHall(id, hall);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/all")
    public List<Hall> show() throws SQLException {
        return hallService.findAllHalls();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/allHallsWithReservations")
    public List<Hall> getAllHallsWithReservations() throws SQLException {
        return hallService.getAllHallsWithReservations();
    }

}
