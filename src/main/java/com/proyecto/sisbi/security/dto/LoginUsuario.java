package com.proyecto.sisbi.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {

	@NotBlank
	private String Dni;
 
	@NotBlank
	private String Clave;

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	 
	
	
	
}
