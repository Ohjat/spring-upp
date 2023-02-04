package com.example.demo.models.models2;
import com.example.demo.models.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
public class employee {



     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    @Max(value = 50000 , message = "перебор")
    @Min(value = 1 , message = "перебор")
    public int salary;


    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    public Date date_emploeed;




     @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
     public User user;

      @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="infa_employee_id")
    public infa_employee infa_employee;



        @OneToOne(mappedBy = "employee")
     public warehouse warehouse;


    public com.example.demo.models.models2.warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(com.example.demo.models.models2.warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @ManyToMany
    @JoinTable (name="linking_a_mentor",
            joinColumns=@JoinColumn (name="rasers_id"),
            inverseJoinColumns=@JoinColumn(name="employee_id"))
    private Set<raser> rasers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getDate_emploeed() {
        return date_emploeed;
    }

    public void setDate_emploeed(Date date_emploeed) {
        this.date_emploeed = date_emploeed;
    }

    public com.example.demo.models.models2.infa_employee getInfa_employee() {
        return infa_employee;
    }

    public void setInfa_employee(com.example.demo.models.models2.infa_employee infa_employee) {
        this.infa_employee = infa_employee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<raser> getRasers() {
        return rasers;
    }

    public void setRasers(Set<raser> rasers) {
        this.rasers = rasers;
    }
}
