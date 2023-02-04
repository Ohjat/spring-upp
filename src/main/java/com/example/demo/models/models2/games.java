package com.example.demo.models.models2;

import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class games {




     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    @Max(value = 100 , message = "перебор")
    @Min(value = 1 , message = "перебор")
    public Integer number;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    public Date date_games;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="track_id")
    private track track;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="typeof_rase_id")
    public type_of_race id_type_of_race;

    @OneToOne(mappedBy = "id_games")
    public raser raser;

    public com.example.demo.models.models2.raser getRaser() {
        return raser;
    }

    public void setRaser(com.example.demo.models.models2.raser raser) {
        this.raser = raser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate_games() {
        return date_games;
    }

    public void setDate_games(Date date_games) {
        this.date_games = date_games;
    }

    public com.example.demo.models.models2.track getTrack() {
        return track;
    }

    public void setTrack(com.example.demo.models.models2.track track) {
        this.track = track;
    }

    public type_of_race getId_type_of_race() {
        return id_type_of_race;
    }

    public void setId_type_of_race(type_of_race id_type_of_race) {
        this.id_type_of_race = id_type_of_race;
    }
}
