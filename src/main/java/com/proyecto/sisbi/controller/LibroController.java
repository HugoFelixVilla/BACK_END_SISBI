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

import com.proyecto.sisbi.dto.EditorialDto;
import com.proyecto.sisbi.dto.LibroDto;
import com.proyecto.sisbi.dto.Mensaje;
import com.proyecto.sisbi.entity.Editorial;
import com.proyecto.sisbi.entity.Libro;
import com.proyecto.sisbi.service.LibroService; 

@RestController
@RequestMapping("/libro")
@CrossOrigin(origins = "http://localhost:8081")
public class LibroController {
	
	@Autowired
	LibroService libroService;

	@GetMapping("/lista")
	public ResponseEntity<List<Libro>> list() {
		List<Libro> list = libroService.list();
		// return new ResponseEntity(list,HttpStatus.OK);

		return new ResponseEntity<List<Libro>>(list, HttpStatus.OK);
	}

	@GetMapping("/datail/{id}")
	public ResponseEntity<Libro> getById(@PathVariable("id") int id) {
		if (!libroService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Libro libro = libroService.getOne(id).get();
		return new ResponseEntity(libro, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody LibroDto libroDto) {

		 if (StringUtils.isBlank(libroDto.getNombre()))
			return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(libroDto.getAnioPublicacion()))
			return new ResponseEntity(new Mensaje("el año es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(libroDto.getNroPaginas()))
			return new ResponseEntity(new Mensaje("el numero de páginas es obligatorio"), HttpStatus.BAD_REQUEST);

		 
	 
		if (libroDto.getNombre().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el nombre debe ser solo letras"), HttpStatus.BAD_REQUEST);

	 
		if (libroDto.getAnioPublicacion().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
			return new ResponseEntity(new Mensaje("el año debe ser solo números"), HttpStatus.BAD_REQUEST);

		if (libroDto.getNroPaginas().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
			return new ResponseEntity(new Mensaje("el numero de páginas debe ser solo números"), HttpStatus.BAD_REQUEST);

		if (libroDto.getAnioPublicacion().toString().length() < 4)
			return new ResponseEntity(new Mensaje("el año debe ser mayor a 3 digitos"), HttpStatus.BAD_REQUEST);

		if (libroDto.getAnioPublicacion().toString().length() > 4)
			return new ResponseEntity(new Mensaje("el año debe ser menor a 5 digitos"), HttpStatus.BAD_REQUEST);
		
		 
		
		if (libroDto.getNroPaginas().toString().length() > 4)
			return new ResponseEntity(new Mensaje("el numero de páginas debe ser menor a 5 digitos"), HttpStatus.BAD_REQUEST);
		
		  
	 	if (libroService.existsByNombre(libroDto.getNombre()))
			return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

	 
	 	if (StringUtils.isBlank(libroDto.getEstado()))
			return new ResponseEntity(new Mensaje("Seleccione un estado"), HttpStatus.BAD_REQUEST);
		
	 	
	 	
	 	Libro libro = new Libro(libroDto.getNombre(), libroDto.getAnioPublicacion(), libroDto.getNroPaginas(), libroDto.getEstado(), libroDto.getEspecialidad(), libroDto.getEditorial());

		libroService.save(libro);
		return new ResponseEntity(new Mensaje("libro creado"), HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody LibroDto libroDto,
			BindingResult bindingResult) {

		if (!libroService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		 
		 if (StringUtils.isBlank(libroDto.getNombre()))
				return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

			if (StringUtils.isBlank(libroDto.getAnioPublicacion()))
				return new ResponseEntity(new Mensaje("el año es obligatorio"), HttpStatus.BAD_REQUEST);

			if (StringUtils.isBlank(libroDto.getNroPaginas()))
				return new ResponseEntity(new Mensaje("el numero de páginas es obligatorio"), HttpStatus.BAD_REQUEST);

			 
		 
			if (libroDto.getNombre().matches(("^[0-9]*$")))
				return new ResponseEntity(new Mensaje("el nombre debe ser solo letras"), HttpStatus.BAD_REQUEST);

		 
			if (libroDto.getAnioPublicacion().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
				return new ResponseEntity(new Mensaje("el año debe ser solo números"), HttpStatus.BAD_REQUEST);

			if (libroDto.getNroPaginas().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
				return new ResponseEntity(new Mensaje("el numero de páginas debe ser solo números"), HttpStatus.BAD_REQUEST);

			if (libroDto.getAnioPublicacion().toString().length() < 4)
				return new ResponseEntity(new Mensaje("el año debe ser mayor a 3 digitos"), HttpStatus.BAD_REQUEST);

			if (libroDto.getAnioPublicacion().toString().length() > 4)
				return new ResponseEntity(new Mensaje("el año debe ser menor a 5 digitos"), HttpStatus.BAD_REQUEST);
			
		 	
			
			if (libroDto.getNroPaginas().toString().length() > 4)
				return new ResponseEntity(new Mensaje("el numero de páginas debe ser menor a 5 digitos"), HttpStatus.BAD_REQUEST);
			
			
			if (StringUtils.isBlank(libroDto.getEstado()))
				return new ResponseEntity(new Mensaje("Seleccione un estado"), HttpStatus.BAD_REQUEST);
			

		
		
		
		if (libroService.existsByNombre(libroDto.getNombre())
				&& libroService.getByNombre(libroDto.getNombre()).get().getID_LIBRO() != id)
			return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		
		

		 
		Libro libro = libroService.getOne(id).get();
		libro.setNombre(libroDto.getNombre());
		libro.setAnioPublicacion(libroDto.getAnioPublicacion());
		libro.setNroPaginas(libroDto.getNroPaginas());
		libro.setEspecialidad(libroDto.getEspecialidad()); 
		libro.setEditorial(libroDto.getEditorial());
		libro.setEstado(libroDto.getEstado());
	 
		libroService.save(libro);

		return new ResponseEntity(new Mensaje("libro actualizado"), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!libroService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		libroService.delete(id);
		return new ResponseEntity(new Mensaje("libro eliminado"), HttpStatus.OK);
	}

}
