package ro.upb.saladeevenimente.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email")
    private  String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "available")
    private Boolean available;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "start_vacation")
    private Date start_vacation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "end_vacation")
    private Date end_vacation;

    @OneToMany(mappedBy = "worker")
    List<SheetHall> sheetHalls;

    @OneToMany(mappedBy = "worker")
    List<SheetUserWorker> sheetUserWorkers;

    public Worker(long id, String first_name, String last_name, String email, String phone,
                  Boolean available, Date start_vacation, Date end_vacation) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.available = available;
        this.start_vacation = start_vacation;
        this.end_vacation = end_vacation;

    }

    public Worker() {}

    public Worker(String first_name, String last_name, Date start_vacation, Date end_vacation) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.start_vacation = start_vacation;
        this.end_vacation = end_vacation;
    }

    public Worker(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstname() { return first_name; }

    public void setFirstname(String first_name) { this.first_name = first_name; }

    public String getLastname() { return last_name; }

    public void setLastname(String last_name) { this.last_name = last_name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public List<SheetHall> getSheetHalls() { return sheetHalls; }

    public void setSheetHalls(List<SheetHall> sheetHalls) { this.sheetHalls = sheetHalls; }

    public List<SheetUserWorker> getSheetUserWorkers() {
        return sheetUserWorkers;
    }

    public void setSheetUserWorkers(List<SheetUserWorker> sheetUserWorkers) {
        this.sheetUserWorkers = sheetUserWorkers;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date getStart_vacation() {
        return start_vacation;
    }

    public void setStart_vacation(Date start_vacation) {
        this.start_vacation = start_vacation;
    }

    public Date getEnd_vacation() {
        return end_vacation;
    }

    public void setEnd_vacation(Date end_vacation) {
        this.end_vacation = end_vacation;
    }



}
