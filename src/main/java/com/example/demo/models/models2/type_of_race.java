package com.example.demo.models.models2;

import com.example.demo.interfaces.IExelExport;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class type_of_race implements IExelExport {

    public type_of_race(String name, Integer nuber_of_riders)
    {
        this.name=name;
        this.nuber_of_riders=nuber_of_riders;
    }
    public  type_of_race()
    {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank
    @Size(min = 1, max = 70 , message = "не правилный ввод данных")
    public String name;

    @NotNull
    @Max(value = 100 , message = "перебор")
    @Min(value = 1 , message = "перебор")
    public Integer nuber_of_riders;


     @OneToOne(mappedBy = "track")
    public games games;


    public com.example.demo.models.models2.games getGames() {
        return games;
    }

    public void setGames(com.example.demo.models.models2.games games) {
        this.games = games;
    }

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

    @Override
    public List<Object> getHeaders() {
        return Arrays.asList("Название трека", "Колличество гонщиков на треке");
    }

    @Override
    public List<Object> getData() {
        return Arrays.asList(name, nuber_of_riders);
    }
}
