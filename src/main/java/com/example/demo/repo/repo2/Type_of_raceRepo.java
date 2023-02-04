package com.example.demo.repo.repo2;

import com.example.demo.models.models2.type_of_race;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Type_of_raceRepo extends CrudRepository<type_of_race, Long > {

     List<type_of_race> findByNameContains(String name);

    List<type_of_race> findByName(String name);

}
