package ro.upb.saladeevenimente.domain;

import javax.persistence.*;

@Entity
public class SheetUserWorker {

    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    Worker worker;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Worker getWorker() { return worker; }

    public void setWorker(Worker worker) { this.worker = worker; }

}
