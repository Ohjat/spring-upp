package com.example.demo.repo.repo2;

import com.example.demo.models.models2.rol;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RolRepo extends CrudRepository<rol, Long > {

     List<rol> findByNameContains(String name);

    List<rol> findByName(String name);

}
