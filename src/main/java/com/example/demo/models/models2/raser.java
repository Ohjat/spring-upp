package com.example.demo.models.models2;

import com.example.demo.models.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class raser {
    public raser(Date data_registr, Cars cars, games games, awards awards, User user)
    {
      this.data_registr= data_registr;
      this.id_cars= cars;
      this.id_games=games;
      this.id_awards=awards;
      this.user = user;

    }
    public raser()
    {

    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public Date data_registr;
    @OneToOne()
    @JoinColumn(name="cars_id")
    public Cars id_cars;
    @OneToOne()
    @JoinColumn(name="games_id")
    public games id_games;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="awards_id")
    public awards id_awards;


    @OneToOne()
    @JoinColumn(name="user_id")
    public User user;

    @ManyToMany
    @JoinTable (name="linking_a_mentor",
            joinColumns=@JoinColumn (name="employee_id"),
            inverseJoinColumns=@JoinColumn(name="rasers_id"))
    private Set<employee> employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_registr() {
        return data_registr;
    }

    public void setData_registr(Date data_registr) {
        this.data_registr = data_registr;
    }

    public Cars getId_cars() {
        return id_cars;
    }

    public void setId_cars(Cars id_cars) {
        this.id_cars = id_cars;
    }

    public games getId_games() {
        return id_games;
    }

    public void setId_games(games id_games) {
        this.id_games = id_games;
    }

    public awards getId_awards() {
        return id_awards;
    }

    public void setId_awards(awards id_awards) {
        this.id_awards = id_awards;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<com.example.demo.models.models2.employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<com.example.demo.models.models2.employee> employee) {
        this.employee = employee;
    }
}
