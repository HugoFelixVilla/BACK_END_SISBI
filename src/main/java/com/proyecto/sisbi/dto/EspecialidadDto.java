package com.proyecto.sisbi.dto;

import javax.validation.constraints.NotBlank;

public class EspecialidadDto {
	@NotBlank
	private String nombre;
	
	
	private String Descripcion;
	
	@NotBlank
	private String Estado;

	public EspecialidadDto() {
		 
	}

	public EspecialidadDto(@NotBlank String nombre, String descripcion, @NotBlank String estado) {
		super();
		this.nombre = nombre;
		Descripcion = descripcion;
		Estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
	
	
	
	
}
