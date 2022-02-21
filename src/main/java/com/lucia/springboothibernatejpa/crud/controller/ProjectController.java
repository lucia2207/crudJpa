package com.lucia.springboothibernatejpa.crud.controller;

import com.lucia.springboothibernatejpa.crud.model.Employee;
import com.lucia.springboothibernatejpa.crud.model.Project;
import com.lucia.springboothibernatejpa.crud.repository.IProjectJpaRepository;
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
public class ProjectController {

    @Autowired
    IProjectJpaRepository projectJpaRepository;

    @PostMapping("/project") //crear proyecto
    public ResponseEntity<Project> createProyecto(@RequestBody Project project) {
        try {
            Project _project = projectJpaRepository
                    .save(new Project(project.getName()));
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @GetMapping("/project") //Traer todos los project
    public ResponseEntity<List<Project>> getAllProjects(){
        try {
            List<Project> projects = new ArrayList<Project>();

            projectJpaRepository.findAll().forEach(projects::add);

            if (projects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/project/{id}") // traer uno solo
    public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {
        Optional<Project> ProyectoData = projectJpaRepository.findById(id);

        if (ProyectoData.isPresent()) {
            return new ResponseEntity<Project>(ProyectoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/project/{id}") //borrar proyecto
    public ResponseEntity<String> deleteProject(@PathVariable("id") long id) {
        try {
            projectJpaRepository.deleteById(id);
            return new ResponseEntity<>("PROJECT DELETE!! ",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/project/{id}") // actualizar proyecto
    public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project project) {
        Optional<Project> projectdata = projectJpaRepository.findById(id);

        if (projectdata.isPresent()){
            Project _proyect = projectdata.get();
            _proyect.setName(project.getName());
            return new ResponseEntity<>(projectJpaRepository.save(_proyect), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
