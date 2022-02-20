package com.lucia.springboothibernatejpa.crud.repository;

import com.lucia.springboothibernatejpa.crud.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoleJpaRepository extends JpaRepository<Role,Long>{
    Role findByName(String name);

}
