package ro.upb.saladeevenimente.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user")
    List<SheetUserWorker> sheetUserWorkers;

    public  User(){};

    public User(long id, String email, String first_name, String last_name) {
        this.id = id;
        this.email = email;
        this.firstName = first_name;
        this.lastName = last_name;
    }

    public User(long id, String first_name, String last_name) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void  setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Reservation> getReservations() { return reservations; }

    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }

    public List<SheetUserWorker> getSheetUserWorkers() {
        return sheetUserWorkers;
    }

    public void setSheetUserWorkers(List<SheetUserWorker> sheetUserWorkers) {
        this.sheetUserWorkers = sheetUserWorkers;
    }

}
