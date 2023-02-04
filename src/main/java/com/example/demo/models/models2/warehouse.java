package com.example.demo.models.models2;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Entity
public class warehouse {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank
    @Size(min = 1, max = 70, message = "не правилный ввод данных")
    public String name;

    @NotNull
    @Max(value = 100 , message = "перебор")
    @Min(value = 1 , message = "перебор")
    public Integer numbers_of_cells;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="details_id")
    public details details;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    public employee employee;


    public com.example.demo.models.models2.details getDetails() {
        return details;
    }

    public void setDetails(com.example.demo.models.models2.details details) {
        this.details = details;
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

    public Integer getNumbers_of_cells() {
        return numbers_of_cells;
    }

    public void setNumbers_of_cells(Integer numbers_of_cells) {
        this.numbers_of_cells = numbers_of_cells;
    }


    public com.example.demo.models.models2.employee getEmployee() {
        return employee;
    }

    public void setEmployee(com.example.demo.models.models2.employee employee) {
        this.employee = employee;
    }
}
