package com.proyecto.sisbi.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.sisbi.dto.EspecialidadDto;
import com.proyecto.sisbi.dto.Mensaje; 
import com.proyecto.sisbi.entity.Especialidad;
import com.proyecto.sisbi.service.EspecialidadService;
 

@RestController
@RequestMapping("/especialidad")
@CrossOrigin(origins = "http://localhost:8081")
public class EspecialidadController {
	
	@Autowired
	EspecialidadService especialidadService;
	
	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@GetMapping("/lista")
	public ResponseEntity<List<Especialidad>> list() {
		List<Especialidad> list = especialidadService.list();
		// return new ResponseEntity(list,HttpStatus.OK);

		return new ResponseEntity<List<Especialidad>>(list, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@GetMapping("/datail/{id}")
	public ResponseEntity<Especialidad> getById(@PathVariable("id") int id) {
		if (!especialidadService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Especialidad especialidad = especialidadService.getOne(id).get();
		return new ResponseEntity(especialidad, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody EspecialidadDto esepecialidadlDto) {

		 

		if (StringUtils.isBlank(esepecialidadlDto.getNombre()))
			return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(esepecialidadlDto.getDescripcion()))
			return new ResponseEntity(new Mensaje("La descripción es obligatorio"), HttpStatus.BAD_REQUEST);
		
		if (StringUtils.isBlank(esepecialidadlDto.getEstado()))
			return new ResponseEntity(new Mensaje("Seleccione un estado"), HttpStatus.BAD_REQUEST);
		

		 

		if (esepecialidadlDto.getNombre().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el nombre debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (esepecialidadlDto.getDescripcion().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("la descripcion debe ser solo letras"), HttpStatus.BAD_REQUEST);

	 
		 	if (especialidadService.existsByNombre(esepecialidadlDto.getNombre()))
			return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

		 
		Especialidad especialidad = new Especialidad(esepecialidadlDto.getNombre(), esepecialidadlDto.getDescripcion(), esepecialidadlDto.getEstado());

		especialidadService.save(especialidad);
		return new ResponseEntity(new Mensaje("especialidad creado"), HttpStatus.OK);
	}
	
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EspecialidadDto especialidadDto,
			BindingResult bindingResult) {

		if (!especialidadService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		 
		if (StringUtils.isBlank(especialidadDto.getNombre()))
			return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(especialidadDto.getDescripcion()))
			return new ResponseEntity(new Mensaje("La descripción es obligatorio"), HttpStatus.BAD_REQUEST);

		 
		 
		if (especialidadDto.getNombre().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el nombre debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (especialidadDto.getDescripcion().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("la descripcion debe ser solo letras"), HttpStatus.BAD_REQUEST);

			
		
		if (especialidadService.existsByNombre(especialidadDto.getNombre())
				&& especialidadService.getByNombre(especialidadDto.getNombre()).get().getID_Especialidad() != id)
			return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

		 
		Especialidad especialidad = especialidadService.getOne(id).get();
		especialidad.setNombre(especialidadDto.getNombre());
		especialidad.setDescripcion(especialidadDto.getDescripcion());
		especialidad.setEstado(especialidadDto.getEstado());
		 

		especialidadService.save(especialidad);

		return new ResponseEntity(new Mensaje("especialidad actualizado"), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!especialidadService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		especialidadService.delete(id);
		return new ResponseEntity(new Mensaje("especialidad eliminado"), HttpStatus.OK);
	}
	

}
