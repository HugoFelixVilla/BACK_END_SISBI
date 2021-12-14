package com.proyecto.sisbi.security.entity;

import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class PersonaPrincipal implements UserDetails {
	 private String Dni;
	 private String Nombre;
	 private String Apellidos; 
	 private String Direccion;
	 private String Telefono;
	 private String CorreoElectronico;
	 private String Clave;
 
	 private String Estado;
	 
	 private Collection<? extends GrantedAuthority>authorities;
	 
	 
	 

	public PersonaPrincipal(String dni, String nombre, String apellidos, String direccion, String telefono,
			String correoElectronico, String clave,
			Collection<? extends GrantedAuthority> authorities) {
		 
		Dni = dni;
		Nombre = nombre;
		Apellidos = apellidos;
		Direccion = direccion;
		Telefono = telefono;
		CorreoElectronico = correoElectronico;
		Clave = clave;
		 
		 
		this.authorities = authorities;
	}
	
	//ASIGNAR LOS PRIVILEGIOS:
		public static PersonaPrincipal build(Persona persona) {
			 
		List<GrantedAuthority>authorities=
				persona.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
						.getRolNombre().name())).collect(Collectors.toList());
		return new PersonaPrincipal(persona.getDni(),persona.getNombre(),
				persona.getApellidos(),persona.getDireccion(),persona.getTelefono(),
				persona.getCorreoElectronico(),persona.getClave(),authorities);

		}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 
		return authorities;
	}

	@Override
	public String getPassword() {
		 
		return Clave;
	}

	@Override
	public String getUsername() {
		 
		return Dni;
	}

	@Override
	public boolean isAccountNonExpired() {
	//	 TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	//		 TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	//		 TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
	//			 TODO Auto-generated method stub
		return true;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public String getDireccion() {
		return Direccion;
	}

	public String getTelefono() {
		return Telefono;
	}

	public String getCorreoElectronico() {
		return CorreoElectronico;
	}

	 
	
	
}
