package com.example.demo.models.models2;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class infa_employee {


    public infa_employee()
    {

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank
    @Size(min = 1, max = 70 , message = "неправильный вод данных")
    public String awards;

    @OneToOne(mappedBy = "infa_employee")
    public employee employee;



     @Min(value = 1 , message = "перебор")
     @Max(value = 100 , message = "перебор")
    public Integer work_experies;

    public com.example.demo.models.models2.employee getEmployee() {
        return employee;
    }

    public void setEmployee(com.example.demo.models.models2.employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public Integer getWork_experies() {
        return work_experies;
    }

    public void setWork_experies(Integer work_experies) {
        this.work_experies = work_experies;
    }
}
