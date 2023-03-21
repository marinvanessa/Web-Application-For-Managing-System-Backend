package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.Worker;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface WorkerJdbcRepository {
    Worker addWorker(Worker worker) throws SQLException;
    List<Worker> findAllWorkers() throws SQLException;
    HashMap<String, Integer> findNumberOfHallsForWorker() throws SQLException;
    List<Worker> findAllWorkersByUserId(Long userId) throws SQLException;
    List<Worker> getWorkersByHallId(Long hallId) throws SQLException;
    int getNoOfWorkersByHallId(Long hallId)throws SQLException;
    List<Worker> getWorkersAvailableForAllReservations() throws SQLException;
    List<Worker> getAllWorkersThatEndsVacationBeforeReservations() throws SQLException;
    List<Worker> getAllWorkersInVacation() throws SQLException;
    List<Worker> getWorkersWithExpensiveHalls() throws SQLException;
    List<Worker> getWorkersWithCheapHalls() throws SQLException;
}
