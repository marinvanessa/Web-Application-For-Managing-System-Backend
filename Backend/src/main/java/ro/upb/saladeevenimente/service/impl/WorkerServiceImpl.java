package ro.upb.saladeevenimente.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.upb.saladeevenimente.domain.Worker;
import ro.upb.saladeevenimente.repository.WorkerJdbcRepository;
import ro.upb.saladeevenimente.repository.WorkerJdbcRepositoryImpl;
import ro.upb.saladeevenimente.service.WorkerService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private WorkerJdbcRepository workerJdbcRepository = new WorkerJdbcRepositoryImpl();

    @Override
    public Worker addWorker(Worker worker) throws SQLException {
        return workerJdbcRepository.addWorker(worker);
    }

    @Override
    public List<Worker> getAllWorkers() throws SQLException {
        return workerJdbcRepository.findAllWorkers();
    }

    @Override
    public HashMap<String, Integer> getNumberOfHallsForWorker() throws SQLException {
        return workerJdbcRepository.findNumberOfHallsForWorker();
    }

    @Override
    public int getNoOfWorkersByHallId(Long hallId) throws SQLException {
        return workerJdbcRepository.getNoOfWorkersByHallId(hallId);
    }

    @Override
    public List<Worker> getAllWorkersByUserId(Long userID) throws SQLException {
        return workerJdbcRepository.findAllWorkersByUserId(userID);
    }

    @Override
    public List<Worker> getWorkersByHallId(Long hallId) throws SQLException {
        return workerJdbcRepository.getWorkersByHallId(hallId);
    }

    @Override
    public List<Worker> getWorkersAvailableForAllReservations() throws SQLException {
        return workerJdbcRepository.getWorkersAvailableForAllReservations();
    }

    @Override
    public List<Worker> getAllWorkersThatEndsVacationBeforeReservations() throws SQLException {
        return workerJdbcRepository.getAllWorkersThatEndsVacationBeforeReservations();
    }

    @Override
    public List<Worker> getAllWorkersInVacation() throws SQLException {
        return workerJdbcRepository.getAllWorkersInVacation();
    }

    @Override
    public List<Worker> getWorkersWithExpensiveHalls() throws SQLException {
        return workerJdbcRepository.getWorkersWithExpensiveHalls();
    }

    @Override
    public List<Worker> getWorkersWithCheapHalls() throws SQLException {
        return workerJdbcRepository.getWorkersWithCheapHalls();
    }

}