package com.proyecto.sisbi.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

 

@Entity
@Table(name="persona_rol")
public class PersonaRol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_PERSONA_ROL;
	 
	
	  
	@ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="ID_PERSONA")
	 private Persona persona;
	
	@ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="ID_ROL")
	 private Rol rol;

	
	
	
	public PersonaRol() {
		 
	}




	public PersonaRol(Persona persona, Rol rol) {
		 
	 
		this.persona = persona;
		this.rol = rol;
	}




	 



 
	public Integer getID_PERSONA_ROL() {
		return ID_PERSONA_ROL;
	}




	public void setID_PERSONA_ROL(Integer iD_PERSONA_ROL) {
		ID_PERSONA_ROL = iD_PERSONA_ROL;
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
