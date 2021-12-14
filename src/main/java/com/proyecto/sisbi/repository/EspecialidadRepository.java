package com.proyecto.sisbi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.sisbi.entity.Especialidad; 
@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
	 Optional<Especialidad> findByNombre(String nombre);
	    boolean existsByNombre(String nombre);

}
