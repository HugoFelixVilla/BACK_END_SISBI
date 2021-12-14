package com.proyecto.sisbi.security.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank; 

import com.proyecto.sisbi.security.entity.Persona;
import com.proyecto.sisbi.security.entity.Rol;

public class PersonarolDto {
	@NotBlank 
	@ManyToOne
	 @JoinColumn(name="ID_PERSONA")
	 private Persona persona;
	
	@NotBlank 
	@ManyToOne
	 @JoinColumn(name="ID_ROL")
	 private Rol rol;

	public PersonarolDto(@NotBlank Persona persona, @NotBlank Rol rol) {
	 
		this.persona = persona;
		this.rol = rol;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	} 
	
	
}
