package com.proyecto.sisbi.security.dto;
 
import javax.validation.constraints.NotBlank; 

public class RolDto {
	@NotBlank
	private String Nombre;
	 
	
	@NotBlank
	private String Estado;

	
	

	 

	public RolDto(@NotBlank String nombre, @NotBlank String estado) {
		 
		Nombre = nombre;
		Estado = estado;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getEstado() {
		return Estado;
	}


	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
}
