package com.lucia.springboothibernatejpa.crud.repository;

import java.util.List;

import com.lucia.springboothibernatejpa.crud.model.Employee;
import com.lucia.springboothibernatejpa.crud.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee, Long> {
    // select fields from employee where employeeid='[param]'
    Employee findByEmployeeid(String employeeid);

    List<Employee> findByFirstName(String firstName);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByRole(Role role);

}
