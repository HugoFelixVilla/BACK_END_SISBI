package com.proyecto.sisbi.security.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.sisbi.entity.Libro;
import com.proyecto.sisbi.security.entity.PersonaRol;
import com.proyecto.sisbi.security.repository.PersonarolRepository;
 

@Service
@Transactional
public class PersonarolService {
	
	@Autowired
    PersonarolRepository personarolRepository;
	
	 public List<PersonaRol> list(){
	        return personarolRepository.findAll();
	    }

	    public Optional<PersonaRol> getOne(int id){
	        return personarolRepository.findById(id);
	    }

	     
	    public void  save(PersonaRol personaRol){
	    	personarolRepository.save(personaRol);
	    }

	    public boolean existsById(int id){
	        return personarolRepository.existsById(id);
	    }
	    
	    
	    


}
