package ro.upb.saladeevenimente.service;

import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.Worker;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public interface WorkerService {
    Worker addWorker(Worker worker) throws SQLException;
    List<Worker> getAllWorkers() throws SQLException;
    HashMap<String, Integer> getNumberOfHallsForWorker() throws SQLException;
    int getNoOfWorkersByHallId(Long hallId) throws SQLException;
    List<Worker> getAllWorkersByUserId(Long userId) throws SQLException;
    List<Worker> getWorkersByHallId(Long hallId) throws SQLException;
    List<Worker> getWorkersAvailableForAllReservations() throws SQLException;
    List<Worker> getAllWorkersThatEndsVacationBeforeReservations() throws SQLException;
    List<Worker> getAllWorkersInVacation() throws SQLException;
    List<Worker> getWorkersWithExpensiveHalls() throws SQLException;
    List<Worker> getWorkersWithCheapHalls() throws SQLException;
}
