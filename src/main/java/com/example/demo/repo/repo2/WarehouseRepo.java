package com.example.demo.repo.repo2;

import com.example.demo.models.models2.warehouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseRepo extends CrudRepository<warehouse, Long > {

     List<warehouse> findByNameContains(String name);

    List<warehouse> findByName(String name);
}
