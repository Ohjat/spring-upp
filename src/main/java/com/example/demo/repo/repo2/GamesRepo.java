package com.example.demo.repo.repo2;


import com.example.demo.models.models2.games;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GamesRepo extends CrudRepository<games, Long > {


    List<games> findByTrackNameContains(String name);

    List<games> findByTrackName(String name);

}
