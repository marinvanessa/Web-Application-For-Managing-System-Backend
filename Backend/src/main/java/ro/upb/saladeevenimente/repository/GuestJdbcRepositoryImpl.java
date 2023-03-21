package ro.upb.saladeevenimente.repository;

import ro.upb.saladeevenimente.domain.Guest;
import ro.upb.saladeevenimente.domain.Hall;
import ro.upb.saladeevenimente.domain.Reservation;
import ro.upb.saladeevenimente.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestJdbcRepositoryImpl implements GuestJdbcRepository{

    @Override
    public Guest addGuest(Guest guest) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("INSERT INTO guest(first_name, last_name, phone_number," +
                "covid_certification, reservation_id)" + "values (?,?,?,?,?)");
        c.setString(1, guest.getFirstName());
        c.setString(2, guest.getLastName());
        c.setString(3, guest.getPhone_number());
        c.setBoolean(4, guest.getCovid_certification());
        c.setLong(5, guest.getReservation().getId());
        boolean resultSet = c.execute();
        return null;
    }

    @Override
    public void deleteGuest(Long guestId) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("DELETE FROM guest where id=?");
        c.setLong(1, guestId);
        c.executeUpdate();
    }

    @Override
    public List<Guest> getAllGuests() throws SQLException {
        List<Guest> guests = new ArrayList<Guest>();
        Guest g = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("select * from guest");
        ResultSet resultSet = c.executeQuery();
        while(resultSet.next()){

            g = new Guest(resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("phone_number"),
                    resultSet.getBoolean("covid_certification"));
            guests.add(g);
        }
        return guests;
    }

    @Override
    public List<Guest> findAllGuestsByReservationId(Long reservationId) throws SQLException {
        List<Guest> guests = new ArrayList<Guest>();
        Guest g = null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("select * from guest where reservation_id=?");
        c.setLong(1, reservationId);
        ResultSet resultSet = c.executeQuery();
        while(resultSet.next()){
            g = new Guest(
                    resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("phone_number"),
                    resultSet.getBoolean("covid_certification"));
            guests.add(g);
        }
        return guests;
    }

    @Override
    public void updateGuest(Long guestId, Guest guest) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");

        PreparedStatement d = connection.prepareStatement("SELECT * FROM guest");
        ResultSet resultSet = d.executeQuery();
        if (resultSet.next()) {
            String prev_firstName = resultSet.getString("first_name");
            String prev_lastName = resultSet.getString("last_name");
            String prev_phoneNr = resultSet.getString("phone_number");

            PreparedStatement c = connection.prepareStatement("UPDATE guest SET first_name=?, last_name=?, phone_number=? " +
                    " WHERE id=?");

            if (guest.getFirstName().equals("")) {
                guest.setFirstName(prev_firstName);
            }
            if (guest.getLastName().equals("")) {
                guest.setLastName(prev_lastName);
            }
            if (guest.getPhone_number().equals("")) {
                guest.setPhone_number(prev_phoneNr);
            }
            c.setString(1, guest.getFirstName());
            c.setString(2, guest.getLastName());
            c.setString(3, guest.getPhone_number());
            c.setLong(4, guestId);
            c.executeUpdate();

        }

    }

    // ==================== #2 SIMPLE SUBQUERY WITH COUNT ====================
    @Override
    public int getTheNumberOfGuests(Long reservationId) throws SQLException {
        int noOfGuests = 0;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proiectbd",
                "root",
                "root");
        PreparedStatement c = connection.prepareStatement("SELECT COUNT(*) AS noGuests " +
                "FROM guest WHERE reservation_id=?");
        c.setLong(1, reservationId);
        ResultSet resultSet = c.executeQuery();
        if (resultSet.next()) {
            noOfGuests = resultSet.getInt(1);
        }
        return noOfGuests;
    }

}
