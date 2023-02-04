package com.example.demo.models.models2;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class track {
    public track(String name,Integer nuber_of_riders)
    {
        this.name=name;
        this.nuber_of_riders=nuber_of_riders;
    }
    public track()
    {

    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank
    @Size(min = 1, max = 70 , message = "не правильный ввод данных")
    public String name;

    @NotNull
    @Max(value = 100 , message = "перебор")
    @Min(value = 1 , message = "перебор")
    public Integer nuber_of_riders;

    @OneToOne(mappedBy = "track")
    public games games;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNuber_of_riders() {
        return nuber_of_riders;
    }

    public void setNuber_of_riders(Integer nuber_of_riders) {
        this.nuber_of_riders = nuber_of_riders;
    }

    public com.example.demo.models.models2.games getGames() {
        return games;
    }

    public void setGames(com.example.demo.models.models2.games games) {
        this.games = games;
    }
}
