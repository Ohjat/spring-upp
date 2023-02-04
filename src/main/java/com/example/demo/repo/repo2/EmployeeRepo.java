package com.example.demo.repo.repo2;

import com.example.demo.models.models2.employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<employee, Long > {

    List<employee> findByUserUsernameContains(String username);

    List<employee> findByUserUsername(String username);

}
