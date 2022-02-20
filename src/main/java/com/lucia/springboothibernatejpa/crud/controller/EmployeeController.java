package com.lucia.springboothibernatejpa.crud.controller;

import com.lucia.springboothibernatejpa.crud.model.Employee;
import com.lucia.springboothibernatejpa.crud.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    IEmployeeJpaRepository employeeJpaRepository;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        try {

            List<Employee> employees = new ArrayList<Employee>();

            employeeJpaRepository.findAll().forEach(employees::add);

            if (employees.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


