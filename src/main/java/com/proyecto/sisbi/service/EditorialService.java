package com.proyecto.sisbi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.sisbi.entity.Editorial;
import com.proyecto.sisbi.repository.EditorialRepository;
 

@Service
@Transactional
public class EditorialService {
	
	
	@Autowired
    EditorialRepository editorialRepository;
	
	 public List<Editorial> list(){
	        return editorialRepository.findAll();
	    }

	    public Optional<Editorial> getOne(int id){
	        return editorialRepository.findById(id);
	    }

	    public Optional<Editorial> getByNombre(String nombre){
	        return editorialRepository.findByNombre(nombre);
	    }
	    
	    public Optional<Editorial> getByEmail(String email){
	        return editorialRepository.findByEmail(email);
	    }
	    
	    public Optional<Editorial> getByRuc(String ruc){
	        return editorialRepository.findByRuc(ruc);
	    }

	    public void  save(Editorial editorial){
	    	editorialRepository.save(editorial);
	    }

	    public void delete(int id){
	    	editorialRepository.deleteById(id);
	    }

	    public boolean existsById(int id){
	        return editorialRepository.existsById(id);
	    }

	    public boolean existsByNombre(String nombre){
	        return editorialRepository.existsByNombre(nombre);
	    }
	    
	    public boolean existsByRuc(String ruc){
	        return editorialRepository.existsByRuc(ruc);
	    }
	    
	    public boolean existsByEmail(String email){
	        return editorialRepository.existsByEmail(email);
	    }

}
