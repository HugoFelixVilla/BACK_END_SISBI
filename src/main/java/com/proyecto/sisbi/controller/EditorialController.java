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
import com.proyecto.sisbi.dto.Mensaje;
import com.proyecto.sisbi.entity.Editorial;
import com.proyecto.sisbi.service.EditorialService;

@RestController
@RequestMapping("/editorial")
@CrossOrigin(origins = "http://localhost:8081")
public class EditorialController {

	@Autowired
	EditorialService editorialService;

	//@PreAuthorize("hasRole('ROLE_USER')") 
	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')")  
	@GetMapping("/lista")
	public ResponseEntity<List<Editorial>> list() {
		List<Editorial> list = editorialService.list();
		// return new ResponseEntity(list,HttpStatus.OK);

		return new ResponseEntity<List<Editorial>>(list, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')")  
	@GetMapping("/datail/{id}")
	public ResponseEntity<Editorial> getById(@PathVariable("id") int id) {
		if (!editorialService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Editorial producto = editorialService.getOne(id).get();
		return new ResponseEntity(producto, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody EditorialDto editorialDto) {

		if (StringUtils.isBlank(editorialDto.getRUC()))
			return new ResponseEntity(new Mensaje("el ruc es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getNombre()))
			return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getDireccion()))
			return new ResponseEntity(new Mensaje("La direccion es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getTelefono1()))
			return new ResponseEntity(new Mensaje("el Telefono es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getCorreoElectronico()))
			return new ResponseEntity(new Mensaje("el correo es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getFechaCreacion()))
			return new ResponseEntity(new Mensaje("la fecha es obligatoria"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getNombre().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el nombre debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getDescripcion().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("la descripcion debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getTelefono1().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
			return new ResponseEntity(new Mensaje("el telefono debe ser solo números"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getTelefono2().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
			return new ResponseEntity(new Mensaje("el telefono 2 debe ser solo números"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getRUC().toString().length() < 12)
			return new ResponseEntity(new Mensaje("el ruc debe ser mayor a 11 digitos"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getRUC().toString().length() > 12)
			return new ResponseEntity(new Mensaje("el ruc debe ser menor a 13 digitos"), HttpStatus.BAD_REQUEST);
		
		if (editorialDto.getTelefono1().toString().length() < 7)
			return new ResponseEntity(new Mensaje("el telefono 1 debe ser mayor a 6 digitos"), HttpStatus.BAD_REQUEST);
		
		if (editorialDto.getTelefono1().toString().length() == 8)
			return new ResponseEntity(new Mensaje("el telefono 1 debe ser de 7 o 9 digitos"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getTelefono1().toString().length() > 9)
			return new ResponseEntity(new Mensaje("el telefono 1 debe ser menor a 10 digitos"), HttpStatus.BAD_REQUEST);
		
		
		if (editorialDto.getTelefono2().toString().length() < 7)
			return new ResponseEntity(new Mensaje("el telefono 2 debe ser mayor a 6 digitos"), HttpStatus.BAD_REQUEST);
		
		if (editorialDto.getTelefono2().toString().length() == 8)
			return new ResponseEntity(new Mensaje("el telefono 2 debe ser de 7 o 9 digitos"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getTelefono2().toString().length() > 9)
			return new ResponseEntity(new Mensaje("el telefono 2 debe ser menor a 10 digitos"), HttpStatus.BAD_REQUEST);

		 
		if (StringUtils.isBlank(editorialDto.getEstado()))
			return new ResponseEntity(new Mensaje("Seleccione un estado"), HttpStatus.BAD_REQUEST);
		

		/*
		  
		 * if (editorialDto.getRUC().chars().count() > 10 ||
		 * editorialDto.getRUC().chars().count() < 12) return new ResponseEntity(new
		 * Mensaje("el ruc debe ser de 12 digitos"), HttpStatus.BAD_REQUEST);
		  
	 
		 */
		
		
		 Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		 
		 String email = editorialDto.getCorreoElectronico();
		 
		 Matcher mather = pattern.matcher(email);
		 
		 if (mather.find() == false)
			 return new ResponseEntity(new Mensaje("correo mal ingresado"), HttpStatus.BAD_REQUEST);
			 
		 
		if (editorialService.existsByRuc(editorialDto.getRUC()))
			return new ResponseEntity(new Mensaje("ese ruc ya existe"), HttpStatus.BAD_REQUEST);

		if (editorialService.existsByNombre(editorialDto.getNombre()))
			return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

		if (editorialService.existsByEmail(editorialDto.getCorreoElectronico()))
			return new ResponseEntity(new Mensaje("ese correo ya existe"), HttpStatus.BAD_REQUEST);

		Editorial editorial = new Editorial(editorialDto.getRUC(), editorialDto.getNombre(),
				editorialDto.getDescripcion(), editorialDto.getDireccion(), editorialDto.getTelefono1(),
				editorialDto.getTelefono2(), editorialDto.getCorreoElectronico(), editorialDto.getFechaCreacion(),
				editorialDto.getEstado());

		editorialService.save(editorial);
		return new ResponseEntity(new Mensaje("editorial creado"), HttpStatus.OK);
	}

	//@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')")  
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EditorialDto editorialDto,
			BindingResult bindingResult) {

		if (!editorialService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		if (StringUtils.isBlank(editorialDto.getRUC()))
			return new ResponseEntity(new Mensaje("el ruc es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getNombre()))
			return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getDireccion()))
			return new ResponseEntity(new Mensaje("La direccion es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getTelefono1()))
			return new ResponseEntity(new Mensaje("el Telefono es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getCorreoElectronico()))
			return new ResponseEntity(new Mensaje("el correo es obligatorio"), HttpStatus.BAD_REQUEST);

		if (StringUtils.isBlank(editorialDto.getFechaCreacion()))
			return new ResponseEntity(new Mensaje("la fecha es obligatoria"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getNombre().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el nombre debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getDescripcion().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("la descripcion debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getTelefono1().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
			return new ResponseEntity(new Mensaje("el telefono debe ser solo números"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getTelefono2().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
			return new ResponseEntity(new Mensaje("el telefono 2 debe ser solo números"), HttpStatus.BAD_REQUEST);
		
		
		

		 
		
		
		if (editorialDto.getRUC().toString().length() < 12)
			return new ResponseEntity(new Mensaje("el ruc debe ser mayor a 11 digitos"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getRUC().toString().length() > 12)
			return new ResponseEntity(new Mensaje("el ruc debe ser menor a 13 digitos"), HttpStatus.BAD_REQUEST);
		
		if (editorialDto.getTelefono1().toString().length() < 7)
			return new ResponseEntity(new Mensaje("el telefono 1 debe ser mayor a 6 digitos"), HttpStatus.BAD_REQUEST);
		
		if (editorialDto.getTelefono1().toString().length() == 8)
			return new ResponseEntity(new Mensaje("el telefono 1 debe ser de 7 o 9 digitos"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getTelefono1().toString().length() > 9)
			return new ResponseEntity(new Mensaje("el telefono 1 debe ser menor a 10 digitos"), HttpStatus.BAD_REQUEST);
		
		
		if (editorialDto.getTelefono2().toString().length() < 7)
			return new ResponseEntity(new Mensaje("el telefono 2 debe ser mayor a 6 digitos"), HttpStatus.BAD_REQUEST);
		
		if (editorialDto.getTelefono2().toString().length() == 8)
			return new ResponseEntity(new Mensaje("el telefono 2 debe ser de 7 o 9 digitos"), HttpStatus.BAD_REQUEST);

		if (editorialDto.getTelefono2().toString().length() > 9)
			return new ResponseEntity(new Mensaje("el telefono 2 debe ser menor a 10 digitos"), HttpStatus.BAD_REQUEST);
		
		
		
		
		 Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		 
		 String email = editorialDto.getCorreoElectronico();
		 
		 Matcher mather = pattern.matcher(email);
		 
		 if (mather.find() == false)
			 return new ResponseEntity(new Mensaje("correo mal ingresado"), HttpStatus.BAD_REQUEST);

		
		
		 if (editorialService.existsByRuc(editorialDto.getRUC())
					&& editorialService.getByRuc(editorialDto.getRUC()).get().getID_EDITORIAL() != id)
				return new ResponseEntity(new Mensaje("ese ruc ya existe"), HttpStatus.BAD_REQUEST);

		 
		if (editorialService.existsByNombre(editorialDto.getNombre())
				&& editorialService.getByNombre(editorialDto.getNombre()).get().getID_EDITORIAL() != id)
			return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);

		if (editorialService.existsByEmail(editorialDto.getCorreoElectronico())
				&& editorialService.getByEmail(editorialDto.getCorreoElectronico()).get().getID_EDITORIAL() != id)
			return new ResponseEntity(new Mensaje("ese correo ya existe"), HttpStatus.BAD_REQUEST);

		Editorial editorial = editorialService.getOne(id).get();
		editorial.setRUC(editorialDto.getRUC());
		editorial.setNombre(editorialDto.getNombre());
		editorial.setDescripcion(editorialDto.getDescripcion());
		editorial.setDireccion(editorialDto.getDireccion());
		editorial.setTelefono1(editorialDto.getTelefono1());
		editorial.setTelefono2(editorialDto.getTelefono2());
		editorial.setCorreoElectronico(editorialDto.getCorreoElectronico());
		editorial.setFechaCreacion(editorialDto.getFechaCreacion());
		editorial.setEstado(editorialDto.getEstado());

		editorialService.save(editorial);

		return new ResponseEntity(new Mensaje("editorial actualizado"), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_BIBLIOTECARIO')") 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!editorialService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		editorialService.delete(id);
		return new ResponseEntity(new Mensaje("editorial eliminado"), HttpStatus.OK);
	}

}
