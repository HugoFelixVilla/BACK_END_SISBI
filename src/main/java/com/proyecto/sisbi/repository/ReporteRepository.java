package com.proyecto.sisbi.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.sisbi.entity.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
	/*
	 * Optional<Reporte> findByNombre(String nombre); boolean existsByNombre(String
	 * nombre);
	 */
}
