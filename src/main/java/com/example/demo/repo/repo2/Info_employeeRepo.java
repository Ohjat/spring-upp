package com.example.demo.repo.repo2;

import com.example.demo.models.models2.infa_employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Info_employeeRepo extends CrudRepository<infa_employee, Long > {

  List<infa_employee> findByAwardsContains(String awards);

    List<infa_employee> findByAwards(String awards);

}
