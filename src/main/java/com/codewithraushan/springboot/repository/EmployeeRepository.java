package com.codewithraushan.springboot.repository;

import com.codewithraushan.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    //define custom query using JPQL with index parameters
    @Query("select e from Employee e where e.firstName =?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);


}
