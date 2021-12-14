package com.proyecto.sisbi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.sisbi.entity.Editorial;
 
@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Integer> {
	 Optional<Editorial> findByNombre(String nombre);
	    boolean existsByNombre(String nombre); 
	    
	    Optional<Editorial> findByRuc(String ruc);
	    boolean existsByRuc(String ruc);
	    
	    
	    Optional<Editorial> findByEmail(String email);
	    boolean existsByEmail(String email);

}
