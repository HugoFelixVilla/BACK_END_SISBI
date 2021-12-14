package com.proyecto.sisbi.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.sisbi.security.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer> {
	Optional<Persona>findByDni(String Dni);
	Optional<Persona>findBycorreoElectronico(String correoElectronico);
	boolean existsByDni(String Dni);
	boolean existsBycorreoElectronico(String correoElectronico);
}
