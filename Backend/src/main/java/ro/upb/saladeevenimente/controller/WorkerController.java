package ro.upb.saladeevenimente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.upb.saladeevenimente.domain.Worker;
import ro.upb.saladeevenimente.service.WorkerService;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("")
    public Worker addWorker(@RequestBody Worker worker) throws SQLException {
        return workerService.addWorker(worker);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("all")
    public List<Worker> getAllWorkers() throws SQLException {
        return workerService.getAllWorkers();
    }


    @CrossOrigin("http://localhost:3000")
    @GetMapping("all/{userId}")
    public List<Worker> getAllWorkersByUserId(@PathVariable Long userId) throws SQLException {
        return workerService.getAllWorkersByUserId(userId);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/hallsPerWorker")
    public HashMap<String, Integer> getNumberOfHallsForWorker() throws SQLException {
        return workerService.getNumberOfHallsForWorker();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/hallsWorkers/{hallId}")
    public int getNoOfWorkersByHallId(@PathVariable Long hallId) throws SQLException {
        return workerService.getNoOfWorkersByHallId(hallId);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/workersReservations/{hallId}")
    public List<Worker> getWorkersByHallId(@PathVariable Long hallId) throws SQLException {
        return workerService.getWorkersByHallId(hallId);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/workersAvailableForAllReservations")
    public List<Worker> getWorkersAvailableForAllReservations() throws SQLException {
        return workerService.getWorkersAvailableForAllReservations();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/workersEndsVacationBeforeReservations")
    public List<Worker> getAllWorkersThatEndsVacationBeforeReservations() throws SQLException {
        return workerService.getAllWorkersThatEndsVacationBeforeReservations();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/workersInVacation")
    public List<Worker> getAllWorkersInVacation() throws SQLException {
        return workerService.getAllWorkersInVacation();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/workersForExpensiveHalls")
    public List<Worker> getWorkersWithExpensiveHalls() throws SQLException {
        return workerService.getWorkersWithExpensiveHalls();
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/workersForCheapHalls")
    public List<Worker> getWorkersWithCheapHalls() throws SQLException {
        return workerService.getWorkersWithCheapHalls();
    }

}
