package com.proyecto.sisbi.security.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.proyecto.sisbi.dto.Mensaje; 
import com.proyecto.sisbi.security.dto.PersonarolDto;
import com.proyecto.sisbi.security.entity.PersonaRol;
import com.proyecto.sisbi.security.service.PersonarolService;
 

@RestController
@RequestMapping("/persona_rol")
@CrossOrigin(origins = "http://localhost:8081")
public class PersonaRolController {
	
	@Autowired
	PersonarolService personarolService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/lista")
	public ResponseEntity<List<PersonaRol>> list() {
		List<PersonaRol> list = personarolService.list();
		// return new ResponseEntity(list,HttpStatus.OK);

		return new ResponseEntity<List<PersonaRol>>(list, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/datail/{id}")
	public ResponseEntity<PersonaRol> getById(@PathVariable("id") int id) {
		if (!personarolService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		PersonaRol personarol = personarolService.getOne(id).get();
		return new ResponseEntity(personarol, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PersonarolDto personarolDto,
			BindingResult bindingResult) {

		if (!personarolService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		   

		 
		PersonaRol personarol = personarolService.getOne(id).get();
		personarol.setPersona(personarolDto.getPersona());
		personarol.setRol(personarolDto.getRol()); 
		
		personarolService.save(personarol);

		return new ResponseEntity(new Mensaje("libro actualizado"), HttpStatus.OK);
	}


}
