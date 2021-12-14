package com.proyecto.sisbi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.sisbi.entity.Especialidad;
import com.proyecto.sisbi.repository.EspecialidadRepository;
 

@Service
@Transactional
public class EspecialidadService {

	@Autowired
    EspecialidadRepository especialidadRepository;
	
	 public List<Especialidad> list(){
	        return especialidadRepository.findAll();
	    }

	    public Optional<Especialidad> getOne(int id){
	        return especialidadRepository.findById(id);
	    }

	    public Optional<Especialidad> getByNombre(String Nombre){
	        return especialidadRepository.findByNombre(Nombre);
	    }

	    public void  save(Especialidad especialidad){
	    	especialidadRepository.save(especialidad);
	    }

	    public void delete(int id){
	    	especialidadRepository.deleteById(id);
	    }

	    public boolean existsById(int id){
	        return especialidadRepository.existsById(id);
	    }

	    public boolean existsByNombre(String nombre){
	        return especialidadRepository.existsByNombre(nombre);
	    }
	
}
