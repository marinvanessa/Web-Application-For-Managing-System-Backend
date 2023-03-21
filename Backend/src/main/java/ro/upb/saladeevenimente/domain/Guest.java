package ro.upb.saladeevenimente.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "covid_certification")
    private Boolean covid_certification;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Guest() {}

    public Guest(long id, String first_name, String last_name, String phone_number, boolean covid_certification) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.phone_number = phone_number;
        this.covid_certification = covid_certification;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getPhone_number() { return phone_number; }

    public void setPhone_number(String phone_number) { this.phone_number = phone_number;}

    public Reservation getReservation() { return reservation; }

    public void setReservation(Reservation reservation) { this.reservation = reservation; }

    public Boolean getCovid_certification() { return covid_certification; }

    public void setCovid_certification(Boolean covid_certification) { this.covid_certification = covid_certification; }


}
