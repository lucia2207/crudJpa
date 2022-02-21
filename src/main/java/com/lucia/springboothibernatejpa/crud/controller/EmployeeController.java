package com.lucia.springboothibernatejpa.crud.controller;

import com.lucia.springboothibernatejpa.crud.model.Employee;
import com.lucia.springboothibernatejpa.crud.model.Role;
import com.lucia.springboothibernatejpa.crud.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    IEmployeeJpaRepository employeeJpaRepository;

    @GetMapping("/employee") // traer todos
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

    @GetMapping("/employee/{id}") // traer uno solo
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        Optional<Employee> employeeData = employeeJpaRepository.findById(id);

        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employee") //crear empleado
    public ResponseEntity<Employee> createEmpleado(@RequestBody Employee empleado) {
        try {
            Employee _empleado = employeeJpaRepository
                    .save(new Employee(empleado.getFirstName(), empleado.getLastName(), empleado.getEmployeeid(), empleado.getRole()));
            return new ResponseEntity<>(empleado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/employee/{id}") //borrar empleado
    public ResponseEntity<String> deleteEmpleado(@PathVariable("id") long id) {
        try {
            employeeJpaRepository.deleteById(id);
            return new ResponseEntity<>("EMPLOYEE DELETE!! ",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmpleado(@PathVariable("id") long id, @RequestBody Employee employee) {
        Optional<Employee> employeeData = employeeJpaRepository.findById(id);

        if (employeeData.isPresent()) {
            Employee _employee = employeeData.get();
            _employee.setFirstName(employee.getFirstName());
            _employee.setLastName(employee.getLastName());
            _employee.setEmployeeid(employee.getEmployeeid());
            _employee.setRole(employee.getRole());
            return new ResponseEntity<>(employeeJpaRepository.save(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


