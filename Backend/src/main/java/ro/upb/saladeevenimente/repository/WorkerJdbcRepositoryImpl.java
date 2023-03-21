package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.Hall;
import ro.upb.saladeevenimente.domain.Reservation;
import ro.upb.saladeevenimente.domain.Worker;

import java.sql.*;
import java.util.*;

public class WorkerJdbcRepositoryImpl implements  WorkerJdbcRepository{

    @Override
    public Worker addWorker(Worker worker) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "INSERT INTO worker(first_name,last_name,email,phone,available,start_vacation,end_vacation)"
                        + "values (?,?,?,?,?,?,?)");
        c.setString(1, worker.getFirstname());
        c.setString(2, worker.getLastname());
        c.setString(3, worker.getEmail());
        c.setString(4, worker.getPhone());
        c.setBoolean(5, worker.getAvailable());
        c.setDate(6, worker.getStart_vacation());
        c.setDate(7, worker.getEnd_vacation());
        boolean resultSet = c.execute();
        return null;
    }

    @Override
    public List<Worker> findAllWorkers() throws SQLException {
        List<Worker> workers = new ArrayList<Worker>();
        Worker w = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "SELECT * FROM worker");
        ResultSet resultSet = c.executeQuery();

        while (resultSet.next()) {

            w = new Worker(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getBoolean("available"),
                    resultSet.getDate("start_vacation"),
                    resultSet.getDate("end_vacation"));
            workers.add(w);
        }
        return workers;
    }

    @Override
    public List<Worker> findAllWorkersByUserId(Long userId) throws SQLException {
        List<Worker> workers = new ArrayList<Worker>();
        Worker w1 = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "SELECT w.* FROM worker w JOIN sheet_user_worker suw on w.id = suw.worker_id " +
                        "JOIN user u on suw.user_id = u.id WHERE u.id=?");
        c.setLong(1, userId);
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            w1 = new Worker(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getBoolean("available"),
                    resultSet.getDate("start_vacation"),
                    resultSet.getDate("end_vacation")
            );
            workers.add(w1);
        }
        return workers;
    }

    // ========================================= Subquery #5 ===========================
    @Override
    public HashMap<String, Integer> findNumberOfHallsForWorker() throws SQLException {
        HashMap<String, Integer> hallsPerWorker = new HashMap<String, Integer>();
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "select w.id, concat(w.first_name,' ',w.last_name) as Name, " +
                        "(select count(*) from sheet_hall sh where sh.worker_id = w.id) " +
                        "AS NoHalls " +
                        "from worker w");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            hallsPerWorker.put(resultSet.getString("Name"), resultSet.getInt("NoHalls"));
        }
        return hallsPerWorker;
    }

    // ========================================= Subquery #6 ===========================

    @Override
    public int getNoOfWorkersByHallId(Long hallId) throws SQLException {
        int noOfWorkers = 0;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "SELECT count(worker_id) AS t FROM sheet_hall sh WHERE " +
                        "sh.hall_id IN (SELECT h.id FROM hall h WHERE h.id=?)");
        c.setLong(1, hallId);
        ResultSet resultSet = c.executeQuery();
        if (resultSet.next()) {
            noOfWorkers = resultSet.getInt(1);
        }
        return noOfWorkers;
    }

    @Override
    public List<Worker> getWorkersByHallId(Long hallId) throws SQLException {
        List<Worker> workers = new ArrayList<Worker>();
        Worker w1 = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "select w.* from worker w join sheet_hall sh on sh.worker_id = w.id " +
                        "join hall h on sh.hall_id = h.id " +
                        "join reservation r on r.hall_id = h.id " +
                        "where h.id = ? group by w.id");
        c.setLong(1, hallId);
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            w1 = new Worker(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getBoolean("available"),
                    resultSet.getDate("start_vacation"),
                    resultSet.getDate("end_vacation")
            );
            workers.add(w1);
        }
        return workers;
    }


    // ========================================= Subquery Complex #1===========================
    //toti angajatii care incep concediu dupa terminarea rezervarilor

    @Override
    public List<Worker> getWorkersAvailableForAllReservations() throws SQLException {
        List<Worker> workers = new ArrayList<Worker>();
        Worker w1 = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "select w.first_name,w.last_name, w.start_vacation, w.end_vacation " +
                        "from worker w " +
                        "where w.id in (select sh.worker_id from sheet_hall sh where hall_id in " +
                        "(select h.id from hall h  where h.id in (select r.hall_id from reservation r))) " +
                        "and w.start_vacation > (select max(reservation_date) from reservation) " +
                        "order by w.start_vacation ASC");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            w1 = new Worker(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDate("start_vacation"),
                    resultSet.getDate("end_vacation"));
            workers.add(w1);
        }
        return workers;
    }

    // ========================================= Subquery Complex #2===========================
    //toti angajatii care termin concediu inainte de inceperea rezervarilor

    @Override
    public List<Worker> getAllWorkersThatEndsVacationBeforeReservations() throws SQLException {
        List<Worker> workers = new ArrayList<Worker>();
        Worker w1 = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "select w.first_name, w.last_name, w.start_vacation, w.end_vacation " +
                        "from worker w " +
                        "where w.id in (select sh.worker_id from sheet_hall sh where hall_id in " +
                        "(select h.id from hall h  where h.id in (select r.hall_id from reservation r))) " +
                        "and w.end_vacation < (select min(reservation_date) from reservation) " +
                        "order by w.end_vacation DESC;");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            w1 = new Worker(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDate("start_vacation"),
                    resultSet.getDate("end_vacation"));
            workers.add(w1);
        }
        return workers;
    }


    // ========================================= Subquery Complex #3===========================
    //toti angajatii care au concediu in timpul rezervarilor


    @Override
    public List<Worker> getAllWorkersInVacation() throws SQLException {
        List<Worker> workers = new ArrayList<Worker>();
        Worker w1 = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "select w.first_name, w.last_name, w.start_vacation, w.end_vacation " +
                        "from worker w " +
                        "where w.id in (select sh.worker_id from sheet_hall sh where hall_id in ( " +
                        "select h.id from hall h  where h.id in (select r.hall_id from reservation r))) " +
                        "and w.start_vacation > (select min(reservation_date) from reservation)  " +
                        "and w.end_vacation < (select max(reservation_date) from reservation) " +
                        "order by w.first_name ASC");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            w1 = new Worker(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getDate("start_vacation"),
                    resultSet.getDate("end_vacation"));
            workers.add(w1);
        }
        return workers;
    }

    // ========================================= Subquery Complex #4===========================
    // toti angajatii care lucreaza la salile cele mai scumpe sortati crescator dupa numele de fam

    @Override
    public List<Worker> getWorkersWithExpensiveHalls() throws SQLException {
        List<Worker> workers = new ArrayList<Worker>();
        Worker w1 = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "select w.first_name, w.last_name " +
                        "from worker w " +
                        "where w.id in (select sh.worker_id from sheet_hall sh where hall_id in ( " +
                        "select h.id from hall h  where h.id in (select r.hall_id from reservation r) " +
                        "and h.price > (select avg(price) from hall))) " +
                        "order by w.first_name ASC");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            w1 = new Worker(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
            workers.add(w1);
        }
        return workers;
    }

    // ========================================= Subquery Complex #5===========================
    // toti angajatii care lucreaza la salile cele mai ieftine sortati crescator dupa numele de fam

    @Override
    public List<Worker> getWorkersWithCheapHalls() throws SQLException {
        List<Worker> workers = new ArrayList<Worker>();
        Worker w1 = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "select w.first_name, w.last_name " +
                        "from worker w " +
                        "where w.id in (select sh.worker_id from sheet_hall sh where hall_id in ( " +
                        "select h.id from hall h  where h.id in (select r.hall_id from reservation r) " +
                        "and h.price < (select avg(price) from hall))) " +
                        "order by w.first_name ASC");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            w1 = new Worker(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"));
            workers.add(w1);
        }
        return workers;
    }


}
