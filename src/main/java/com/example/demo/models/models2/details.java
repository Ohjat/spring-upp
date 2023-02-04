package com.example.demo.models.models2;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class details {



     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    @Size(min = 1, max = 70, message = "Максимальное количество символов 70")
    public String name;

    @NotNull
    @Min(value = 1 ,message = "не добор")
    @Max(value = 100, message = "перебор")
    public Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cars_id")
    public Cars cars;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @OneToOne(mappedBy = "details")
     public warehouse warehouse;

    public com.example.demo.models.models2.warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(com.example.demo.models.models2.warehouse warehouse) {
        this.warehouse = warehouse;
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

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }




}
