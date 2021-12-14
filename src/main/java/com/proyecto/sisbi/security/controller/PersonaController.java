package com.proyecto.sisbi.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.proyecto.sisbi.entity.Editorial;
import com.proyecto.sisbi.entity.Especialidad;
import com.proyecto.sisbi.entity.Libro;
import com.proyecto.sisbi.security.dto.JwtDto;
import com.proyecto.sisbi.security.dto.LoginUsuario;
import com.proyecto.sisbi.security.dto.NuevoUsuario;
import com.proyecto.sisbi.security.dto.UsuarioDto;
import com.proyecto.sisbi.security.entity.Persona;
import com.proyecto.sisbi.security.entity.Rol;
import com.proyecto.sisbi.security.enums.RolNombre;
import com.proyecto.sisbi.security.jwt.JwtProvider;
import com.proyecto.sisbi.security.service.PersonaService;
import com.proyecto.sisbi.security.service.RolService; 

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class PersonaController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PersonaService personaService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	

	@PostMapping("/nuevo")
	public ResponseEntity<?>nuevo(@Valid @RequestBody  NuevoUsuario nuevoUsuario,BindingResult bindingResult){
 	if(bindingResult.hasErrors())
	 	return new ResponseEntity(new Mensaje("CAMPOS MAL PUESTOS"),HttpStatus.BAD_REQUEST);
		
		if(personaService.exitsByDNI(nuevoUsuario.getDni()))
			return new ResponseEntity(new Mensaje("Ese DNI ya existe"),HttpStatus.BAD_REQUEST);
		
				if(personaService.exitsBycorreoElectronico(nuevoUsuario.getCorreoElectronico()))
			return new ResponseEntity(new Mensaje("Ese email ya existe"),HttpStatus.BAD_REQUEST);
		
		if (nuevoUsuario.getNombre().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el nombre debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (nuevoUsuario.getApellidos().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el apellido debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (nuevoUsuario.getTelefono().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
			return new ResponseEntity(new Mensaje("el telefono debe ser solo números"), HttpStatus.BAD_REQUEST);
		
		
		if (nuevoUsuario.getTelefono().toString().length() < 7)
			return new ResponseEntity(new Mensaje("el telefono debe ser mayor a 6 digitos"), HttpStatus.BAD_REQUEST);
		
		if (nuevoUsuario.getTelefono().toString().length() == 8)
			return new ResponseEntity(new Mensaje("el telefono  debe ser de 7 o 9 digitos"), HttpStatus.BAD_REQUEST);


		 Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		 
		 String email = nuevoUsuario.getCorreoElectronico();
		 
		 Matcher mather = pattern.matcher(email);
		 
		 if (mather.find() == false)
			 return new ResponseEntity(new Mensaje("correo mal ingresado"), HttpStatus.BAD_REQUEST);
		 
		 
		 if (nuevoUsuario.getDni().toString().length() < 8)
				return new ResponseEntity(new Mensaje("el dni debe ser mayor a 7 digitos"), HttpStatus.BAD_REQUEST);

			if (nuevoUsuario.getDni().toString().length() > 8)
				return new ResponseEntity(new Mensaje("el dni debe ser menor a 9 digitos"), HttpStatus.BAD_REQUEST);
		
		
		
		Persona persona=
				new Persona(nuevoUsuario.getDni(),nuevoUsuario.getNombre(),nuevoUsuario.getApellidos(),
						nuevoUsuario.getDireccion(),nuevoUsuario.getTelefono(),
						nuevoUsuario.getCorreoElectronico(),
						passwordEncoder.encode(nuevoUsuario.getClave()),nuevoUsuario.getEstado());
		
	   //CREAMOS ROLES
		
		Set<Rol>roles=new HashSet<>();
		if(nuevoUsuario.getRoles().contains("ROLE_USER"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		//roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		
		if(nuevoUsuario.getRoles().contains("ROLE_ADMIN"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		
		if(nuevoUsuario.getRoles().contains("ROLE_BIBLIOTECARIO"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_BIBLIOTECARIO).get());
		
		//ASIGNAR ROLES
		
		persona.setRoles(roles);
		
		personaService.save(persona);
		return new ResponseEntity(new Mensaje("USUARIO GUARDADO"),HttpStatus.CREATED);
		
	}
	
 
	@RequestMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("CAMPOS MAL PUESTOS"),HttpStatus.BAD_REQUEST);
		
		Authentication authentication =
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getDni(),loginUsuario.getClave()));	
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt=jwtProvider.generateToken(authentication);
		UserDetails  userDetails=(UserDetails)authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());
		return new ResponseEntity(jwtDto,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@GetMapping("/lista")
	public ResponseEntity<List<Persona>> list() {
		List<Persona> list = personaService.list();
		// return new ResponseEntity(list,HttpStatus.OK);

		return new ResponseEntity<List<Persona>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/datail/{id}")
	public ResponseEntity<Libro> getById(@PathVariable("id") int id) {
		if (!personaService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Persona persona = personaService.getOne(id).get();
		return new ResponseEntity(persona, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!personaService.existsById(id))
			return new ResponseEntity(new Mensaje("NO EXISTE EL ID"), HttpStatus.NOT_FOUND);

		personaService.delete(id);
		return new ResponseEntity(new Mensaje("PERSONA ELIMINADO"), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')") 
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UsuarioDto usuarioDto,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
		 	return new ResponseEntity(new Mensaje("CAMPOS MAL PUESTOS"),HttpStatus.BAD_REQUEST);

		if (!personaService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		 
		 		
				if (personaService.exitsByDNI(usuarioDto.getDni())
						&& personaService.getByDNI(usuarioDto.getDni()).get().getID_Persona() != id)
					return new ResponseEntity(new Mensaje("ese DNI ya existe"), HttpStatus.BAD_REQUEST);

				if (personaService.exitsBycorreoElectronico(usuarioDto.getCorreoElectronico())
						&& personaService.getBycorreoElectronico(usuarioDto.getCorreoElectronico()).get().getID_Persona() != id)
					return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);

		
		if (usuarioDto.getNombre().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el nombre debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (usuarioDto.getApellidos().matches(("^[0-9]*$")))
			return new ResponseEntity(new Mensaje("el apellido debe ser solo letras"), HttpStatus.BAD_REQUEST);

		if (usuarioDto.getTelefono().matches(("^[a-zA-Zá-úÁ-ÚñÑ ]+$")))
			return new ResponseEntity(new Mensaje("el telefono debe ser solo números"), HttpStatus.BAD_REQUEST);
		
		
		if (usuarioDto.getTelefono().toString().length() < 7)
			return new ResponseEntity(new Mensaje("el telefono debe ser mayor a 6 digitos"), HttpStatus.BAD_REQUEST);
		
		if (usuarioDto.getTelefono().toString().length() == 8)
			return new ResponseEntity(new Mensaje("el telefono  debe ser de 7 o 9 digitos"), HttpStatus.BAD_REQUEST);


		 Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		 
		 String email = usuarioDto.getCorreoElectronico();
		 
		 Matcher mather = pattern.matcher(email);
		 
		 if (mather.find() == false)
			 return new ResponseEntity(new Mensaje("correo mal ingresado"), HttpStatus.BAD_REQUEST);
		 
		 
		 if (usuarioDto.getDni().toString().length() < 8)
				return new ResponseEntity(new Mensaje("el dni debe ser mayor a 7 digitos"), HttpStatus.BAD_REQUEST);

			if (usuarioDto.getDni().toString().length() > 8)
				return new ResponseEntity(new Mensaje("el dni debe ser menor a 9 digitos"), HttpStatus.BAD_REQUEST);
		
		 
	 
		 
		Persona persona = personaService.getOne(id).get();
		persona.setDni(usuarioDto.getDni());
		persona.setNombre(usuarioDto.getNombre());
		persona.setApellidos(usuarioDto.getApellidos());
		persona.setDireccion(usuarioDto.getDireccion());
		persona.setTelefono(usuarioDto.getTelefono());
		persona.setCorreoElectronico(usuarioDto.getCorreoElectronico());
		persona.setClave(passwordEncoder.encode(usuarioDto.getClave()));
		persona.setEstado(usuarioDto.getEstado()); 
		 

		personaService.save(persona);
		 
		return new ResponseEntity(new Mensaje("PERSONA ACTUALIZADO"), HttpStatus.OK);
	}
	
	

		

}

