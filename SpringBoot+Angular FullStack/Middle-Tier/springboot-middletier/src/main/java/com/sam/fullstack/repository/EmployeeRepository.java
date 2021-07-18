package com.sam.fullstack.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sam.fullstack.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {

}
