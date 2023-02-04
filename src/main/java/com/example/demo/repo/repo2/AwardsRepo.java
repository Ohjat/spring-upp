package com.example.demo.repo.repo2;

import com.example.demo.models.models2.awards;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AwardsRepo extends CrudRepository<awards, Long > {

    List<awards> findByNameContains(String name);

    List<awards> findByName(String name);

}
