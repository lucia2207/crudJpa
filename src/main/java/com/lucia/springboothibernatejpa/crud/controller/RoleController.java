package com.lucia.springboothibernatejpa.crud.controller;

import com.lucia.springboothibernatejpa.crud.model.Employee;
import com.lucia.springboothibernatejpa.crud.model.Role;
import com.lucia.springboothibernatejpa.crud.repository.IRoleJpaRepository;
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
public class RoleController {

    @Autowired
    IRoleJpaRepository roleJpaRepository;

    @GetMapping("/role") //traer todos
    public ResponseEntity<List<Role>> getAllRoles(){
        try {
            List<Role> roles = new ArrayList<Role>();

            roleJpaRepository.findAll().forEach(roles::add);

            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/role/{id}") //traer uno solo
    public ResponseEntity<Role> getRolelById(@PathVariable("id") long id) {
        Optional<Role> rol = roleJpaRepository.findById(id);

        if (rol.isPresent()) {
            return new ResponseEntity<Role>(rol.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/role") //crear
    public ResponseEntity<Role> crearRol(@RequestBody Role role) {
        try {
            Role _rol = roleJpaRepository
                    .save(new Role(role.getName()));
            return new ResponseEntity<Role>(_rol, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/role/{id}") //borrar empleado
    public ResponseEntity<String> deleteRole(@PathVariable("id") long id) {
        try {
            roleJpaRepository.deleteById(id);
            return new ResponseEntity<>("ROLE DELETE!! ",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") long id, @RequestBody Role role) {
        Optional<Role> roleData = roleJpaRepository.findById(id);

        if (roleData.isPresent()) {
            Role _role = roleData.get();
            _role.setName(role.getName());
            return new ResponseEntity<>(roleJpaRepository.save(_role), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
