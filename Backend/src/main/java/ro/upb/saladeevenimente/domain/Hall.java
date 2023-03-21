package ro.upb.saladeevenimente.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "dimension")
    private Long dimension;

    @Column(name = "price")
    private int price;

    @JsonIgnore
    @OneToMany(mappedBy = "hall")
    private List<Reservation> reservationList;

    @OneToMany(mappedBy = "hall")
    List<SheetHall> sheetHalls;

    public Hall() {}

    public Hall(long id, String name, long dimension, String location) {
        this.id = id;
        this.name = name;
        this.dimension = dimension;
        this.location = location;
    }

    public Hall(long id, long dimension, String location, String name) {
        this.id = id;
        this.dimension = dimension;
        this.location = location;
        this.name = name;
    }

    public Hall(long id, String name, long dimension, String location, int price) {
        this.id = id;
        this.name = name;
        this.dimension = dimension;
        this.location = location;
        this.price = price;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public Long getDimension() { return dimension; }

    public void setDimension(Long dimension) { this.dimension = dimension; }

    public List<Reservation> getReservationList() { return reservationList; }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<SheetHall> getSheetHalls() { return sheetHalls; }

    public void setSheetHalls(List<SheetHall> sheetHalls) { this.sheetHalls = sheetHalls; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }


}
