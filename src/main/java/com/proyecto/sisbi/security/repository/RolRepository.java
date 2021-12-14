package com.proyecto.sisbi.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.sisbi.security.dto.RolDto;
import com.proyecto.sisbi.security.entity.Rol;
import com.proyecto.sisbi.security.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	Optional<Rol>findByRolNombre(RolNombre rolnombre);
	// boolean existsByNombre(String nombre); 
}
