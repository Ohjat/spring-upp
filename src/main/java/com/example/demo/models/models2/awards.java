package com.example.demo.models.models2;


import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class awards {
    public awards(String name) {
        this.name = name;
    }
    public awards() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank
    @Size(min = 1, max = 70, message = "Не правильный ввод даных")
    public String name;

    @NotNull
    @Min(value = 1 ,message = "не добор")
    @Max(value = 100, message = "перебор")
    public Integer number;

    @OneToOne(mappedBy = "id_awards")
    @NotNull
    public raser raser;

    public com.example.demo.models.models2.raser getRaser() {
        return raser;
    }

    public void setRaser(com.example.demo.models.models2.raser raser) {
        this.raser = raser;
    }

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    public Date date;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer nuber) {
        this.number = nuber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
