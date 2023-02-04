package com.example.demo.repo.repo2;

import com.example.demo.models.models2.details;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface

DetailsRepo extends CrudRepository<details, Long > {

    List<details> findByNameContains(String name);

    List<details> findByName(String name);


}
