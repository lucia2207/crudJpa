package com.lucia.springboothibernatejpa.crud.controller;

import com.lucia.springboothibernatejpa.crud.model.Role;
import com.lucia.springboothibernatejpa.crud.repository.IRoleJpaRepository;
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
public class RoleController {

    @Autowired
    IRoleJpaRepository roleJpaRepository;

    @GetMapping("/role")
    public ResponseEntity<List<Role>> getAllRoles(){
        try {
            List<Role> roles = new ArrayList<Role>();

            roleJpaRepository.findAll().forEach(roles::add);

            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(roles, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
