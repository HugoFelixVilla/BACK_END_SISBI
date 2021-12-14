package com.proyecto.sisbi.security.service;

 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.sisbi.entity.Editorial;
import com.proyecto.sisbi.security.entity.Persona;
import com.proyecto.sisbi.security.repository.PersonaRepository;

@Service
@Transactional
public class PersonaService {
	@Autowired
	PersonaRepository personaRepository;
	
	
	 public List<Persona> list(){
	        return personaRepository.findAll();
	    }
	
	public Optional<Persona> getByDNI(String Dni){
		return personaRepository.findByDni(Dni);
	}
	
	
	public boolean exitsByDNI(String Dni) {
		return personaRepository.existsByDni(Dni);
	}
	
public boolean exitsBycorreoElectronico(String correoElectronico) {
		return personaRepository.existsBycorreoElectronico(correoElectronico);
			}


public Optional<Persona> getBycorreoElectronico(String correoElectronico){
	return personaRepository.findBycorreoElectronico(correoElectronico);
}
	
	public void save(Persona persona) {
		personaRepository.save(persona);
	}
	
	 public boolean existsById(int id){
	        return personaRepository.existsById(id);
	    }
	 
	 public void delete(int id){
		 personaRepository.deleteById(id);
	    }
	 
	 public Optional<Persona> getOne(int id){
	        return personaRepository.findById(id);
	    }
 
}
