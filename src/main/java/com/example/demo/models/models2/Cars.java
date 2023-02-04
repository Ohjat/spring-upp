package com.example.demo.models.models2;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Cars {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 70 , message = "Не правильный ввод даных")
    public String name;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 70, message = "Не правильный ввод даных")
    public String color;
    @NotNull
    public Boolean repair;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="details_id")
    public details details;

    @OneToOne(mappedBy = "id_cars")
    public raser raser;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getRepair() {
        return repair;
    }

    public void setRepair(Boolean repair) {
        this.repair = repair;
    }

    public com.example.demo.models.models2.details getDetails() {
        return details;
    }

    public void setDetails(com.example.demo.models.models2.details details) {
        this.details = details;
    }
}
