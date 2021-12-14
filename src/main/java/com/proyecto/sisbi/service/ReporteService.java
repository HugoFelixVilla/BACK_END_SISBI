package com.proyecto.sisbi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.sisbi.entity.Reporte;
import com.proyecto.sisbi.repository.ReporteRepository;
 

@Service
@Transactional
public class ReporteService {
	
	@Autowired
    ReporteRepository reporteRepository;
	
	 public List<Reporte> list(){
	        return reporteRepository.findAll();
	    }

	    public Optional<Reporte> getOne(int id){
	        return reporteRepository.findById(id);
	    }

		/*
		 * public Optional<Reporte> getByNombre(String nombre){ return
		 * reporteRepository.findByNombre(nombre); }
		 */

	    public void  save(Reporte reporte){
	    	reporteRepository.save(reporte);
	    }

	    public void delete(int id){
	    	reporteRepository.deleteById(id);
	    }

	    public boolean existsById(int id){
	        return reporteRepository.existsById(id);
	    }

		/*
		 * public boolean existsByNombre(String nombre){ return
		 * reporteRepository.existsByNombre(nombre); }
		 */

}
