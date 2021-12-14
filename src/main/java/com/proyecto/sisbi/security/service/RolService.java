package com.proyecto.sisbi.security.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.sisbi.security.dto.RolDto;
import com.proyecto.sisbi.security.entity.Rol;
import com.proyecto.sisbi.security.enums.RolNombre;
import com.proyecto.sisbi.security.repository.RolRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	RolRepository rolrepository;
	
	public Optional<Rol>getByRolNombre (RolNombre rolNombre){
		return rolrepository.findByRolNombre(rolNombre);
	}
	
	//public void save(Rol rol) {
//		rolrepository.save(rol);
	//}
	
 public List<Rol> list(){
	        return rolrepository.findAll();
	    }
 
 
 //public List<RolDto> listRol(){
 //    return rolrepository.findAll();
 //}
//	 
//	 public Optional<Rol> getOne(int id){
//	        return rolrepository.findById(id);
//	    }
//	 
//	 public boolean existsById(int id){
//	        return rolrepository.existsById(id);
//	    }
//	 
//	 public boolean existsByNombre(String nombre){
//	        return rolrepository.existsByNombre(nombre);
//	    }
}
