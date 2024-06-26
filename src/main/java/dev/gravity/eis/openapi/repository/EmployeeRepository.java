package dev.gravity.eis.openapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.gravity.eis.openapi.entity.Employee;
import dev.gravity.eis.openapi.entity.Gender;

import java.util.List;

public interface  EmployeeRepository extends JpaRepository<Employee,Integer> {

    public List<Employee> findByGender(Gender gender);

    @Query("select e from Employee e where e.gender = 'M'")
    public  List<Employee> searchByGender(Gender gender);
}
