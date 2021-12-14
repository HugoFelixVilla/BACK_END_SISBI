package com.proyecto.sisbi.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.sisbi.security.entity.PersonaRol; 

@Repository
public interface PersonarolRepository extends JpaRepository<PersonaRol, Integer> {

}
