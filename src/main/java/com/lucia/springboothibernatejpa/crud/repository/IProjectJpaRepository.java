package com.lucia.springboothibernatejpa.crud.repository;

import com.lucia.springboothibernatejpa.crud.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProjectJpaRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}

