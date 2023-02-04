package com.example.demo.repo.repo2;

import com.example.demo.models.models2.Cars;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarsRepo extends CrudRepository<Cars, Long > {

 List<Cars> findByNameContains(String name);

    List<Cars> findByName(String name);

}
