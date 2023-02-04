package com.example.demo.repo.repo2;

import com.example.demo.models.models2.track;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepo extends CrudRepository<track, Long > {

    List<track> findByNameContains(String name);

    List<track> findByName(String name);

}
