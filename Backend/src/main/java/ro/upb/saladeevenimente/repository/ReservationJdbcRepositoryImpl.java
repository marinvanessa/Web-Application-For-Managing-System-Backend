package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.User;
import ro.upb.saladeevenimente.domain.Hall;
import ro.upb.saladeevenimente.domain.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class ReservationJdbcRepositoryImpl implements ReservationJdbcRepository{

    @Override
    public Reservation addReservation(Reservation reservation) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("INSERT INTO reservation(confirmed, " +
                "description, reservation_date, time, hall_id, user_id)" + "values (?,?,?,?,?,?)");
        c.setBoolean(1, false);
        c.setString(2, reservation.getDescription());
        c.setDate(3, Date.valueOf(reservation.getReservationDate()));
        c.setString(4, reservation.getTime());
        c.setLong(5, reservation.getHall().getId());
        c.setLong(6,reservation.getUser().getId());
        boolean resultSet = c.execute();
        return null;
    }

    @Override
    public void deleteReservation(Long reservationId) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement d = connection.prepareStatement("DELETE FROM guest where reservation_id=?");
        d.setLong(1, reservationId);
        d.executeUpdate();
        PreparedStatement c = connection.prepareStatement("DELETE FROM reservation where id=?");
        c.setLong(1, reservationId);
        c.executeUpdate();
    }

    @Override
    public List<Reservation> findAllReservationsByUserId(Long userId) throws SQLException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        Reservation reservation1 = null;
        Hall h = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("select * from reservation");
        ResultSet resultSet = c.executeQuery();
        while(resultSet.next()){
            Long hallId = resultSet.getLong("hall_id");
            PreparedStatement c1 = connection.prepareStatement("select * from hall where id=?");
            c1.setLong(1, hallId);
            ResultSet resultSet1 = c1.executeQuery();

            if (resultSet1.next()) {
                h = new Hall(resultSet1.getLong("id"),
                        resultSet1.getString("location"),
                        resultSet1.getLong("dimension"),
                        resultSet1.getString("name"),
                        resultSet1.getInt("price"));
            }
            reservation1 = new Reservation(
                    resultSet.getLong("id"),
                    resultSet.getBoolean("confirmed"),
                    resultSet.getString("description"),
                    resultSet.getDate("reservation_date").toLocalDate(),
                    resultSet.getString("time"),
                    h);
            reservations.add(reservation1);
        }
        return reservations;
    }

    @Override
    public void confirmReservation(Long reservationId, Reservation reservation) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("UPDATE reservation SET confirmed=true WHERE id=?");
        c.setLong(1, reservationId);
        c.executeUpdate();
    }

    @Override
    public void editReservation(Long reservationId, Reservation reservation) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement d = connection.prepareStatement("SELECT * FROM reservation");
        ResultSet resultSet = d.executeQuery();
        if (resultSet.next()) {
            String prev_description = resultSet.getString("description");
            LocalDate prev_reservation_date = resultSet.getDate("reservation_date").toLocalDate();
            String prev_time = resultSet.getString("time");

            PreparedStatement c = connection.prepareStatement("UPDATE reservation SET description=?, reservation_date=?," +
                    " time=? WHERE id=?");
            if (reservation.getDescription().equals("")) {
               reservation.setDescription(prev_description);
            }
            if (reservation.getReservationDate() == null) {
                reservation.setReservationDate(prev_reservation_date);
            }
            if (reservation.getTime().equals("")) {
                reservation.setTime(prev_time);
            }
            c.setString(1, reservation.getDescription());
            c.setDate(2, Date.valueOf(reservation.getReservationDate()));
            c.setString(3, reservation.getTime());
            c.setLong(4, reservationId);
            c.executeUpdate();

        }

    }
//     ma mai gandesc daca te tin aici
    @Override
    public List<Reservation> getAllConfirmedReservations(Boolean value) throws SQLException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        Reservation res = null;
        Hall myHall = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "SELECT r.id, r.hall_id, confirmed, description, reservation_date, time FROM reservation r " +
                        "INNER JOIN hall h ON r.hall_id = h.id" +
                        " WHERE r.confirmed = ?");
        c.setBoolean(1, value);
        ResultSet resultSet = c.executeQuery();

        while (resultSet.next()) {
            Long myHallId = resultSet.getLong("hall_id");
            PreparedStatement d = connection.prepareStatement("SELECT * FROM hall WHERE id=?");
            d.setLong(1, myHallId);
            ResultSet resultSet1 = d.executeQuery();
            if (resultSet1.next()) {
                myHall = new Hall(
                        resultSet1.getLong("id"),
                        resultSet1.getString("location"),
                        resultSet1.getLong("dimension"),
                        resultSet1.getString("name"),
                        resultSet1.getInt("price"));
            }
            res = new Reservation(
                    resultSet.getLong("id"),
                    resultSet.getBoolean("confirmed"),
                    resultSet.getString("description"),
                    resultSet.getDate("reservation_date").toLocalDate(),
                    resultSet.getString("time"),
                    myHall);
            reservations.add(res);
        }
        return reservations;
    }

    // ========================================= Subquery #1 ===========================
    @Override
    public List<Reservation> getAllReservationsWithoutGuests() throws SQLException {
        List<Reservation> reservationArrayList = new ArrayList<Reservation>();
        Reservation r = null;
        Hall h = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "SELECT * FROM reservation " +
                        "WHERE reservation.id " +
                        "NOT IN (SELECT g.reservation_id FROM guest g JOIN reservation r ON r.id = g.reservation_id)");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {

            Long hallId = resultSet.getLong("hall_id");
            PreparedStatement c1 = connection.prepareStatement("select * from hall where id=?");
            c1.setLong(1, hallId);
            ResultSet resultSet1 = c1.executeQuery();
            if (resultSet1.next()) {
                h = new Hall(resultSet1.getLong("id"),
                        resultSet1.getString("location"),
                        resultSet1.getLong("dimension"),
                        resultSet1.getString("name"),
                        resultSet1.getInt("price"));
            }

            r = new Reservation(
                    resultSet.getLong("id"),
                    resultSet.getBoolean("confirmed"),
                    resultSet.getString("description"),
                    resultSet.getDate("reservation_date").toLocalDate(),
                    resultSet.getString("time"), h);
            reservationArrayList.add(r);
        }
        return reservationArrayList;
    }

    // ========================================= Subquery #2 ===========================
    @Override
    public List<String> getReservationByHallDimension() throws SQLException {
        List<String> reservations_descriptions = new ArrayList<String>();
        String r_description = null;
        Hall h = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "SELECT r.description as Rname FROM reservation R " +
                        "JOIN hall h ON h.id = R.hall_id WHERE h.dimension = " +
                        "(SELECT max(h.dimension) FROM reservation R JOIN hall h ON R.hall_id = h.id) " +
                        "ORDER BY Rname ASC");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            r_description = resultSet.getString("Rname");
            reservations_descriptions.add(r_description);
        }
        return reservations_descriptions;
    }

    // ========================================= Subquery #3 ===========================

    @Override
    public List<String> getCheapReservations() throws SQLException {
        List<String> cheap_reservations = new ArrayList<String>();
        String rd = null;
        Hall h = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "SELECT r.description AS Rname " +
                        "FROM reservation r " +
                        "JOIN hall h ON r.hall_id = h.id " +
                        "WHERE h.price < (SELECT avg(h.price) FROM hall h)\n" +
                        "ORDER BY Rname ASC;");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            rd = resultSet.getString("Rname");
            cheap_reservations.add(rd);
        }
        return cheap_reservations;
    }

    // ========================================= Subquery #4 ===========================

    @Override
    public List<String> getExpensiveReservations() throws SQLException {
        List<String> expensive_reservations = new ArrayList<String>();
        String rd = null;
        Hall h = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement(
                "SELECT r.description AS Rname " +
                        "FROM reservation r " +
                        "JOIN hall h ON r.hall_id = h.id " +
                        "WHERE h.price > (SELECT avg(h.price) FROM hall h)\n" +
                        "ORDER BY Rname ASC;");
        ResultSet resultSet = c.executeQuery();
        while (resultSet.next()) {
            rd = resultSet.getString("Rname");
            expensive_reservations.add(rd);
        }
        return expensive_reservations;
    }

}
