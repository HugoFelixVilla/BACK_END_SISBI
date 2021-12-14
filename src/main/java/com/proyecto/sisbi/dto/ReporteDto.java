package com.proyecto.sisbi.dto;

import javax.validation.constraints.NotBlank;

import com.proyecto.sisbi.entity.Libro;
import com.proyecto.sisbi.security.entity.Persona;

public class ReporteDto {
	 
	@NotBlank
	private String Fecha_Creacion;
	 
	private String Descripcion;
	@NotBlank
	private int Estado;
	@NotBlank
	private Libro libro;
	@NotBlank
	private Persona persona;
	
	
	public ReporteDto() {
		 
	}


	public ReporteDto(@NotBlank String fecha_Creacion, String descripcion,
			@NotBlank int estado, @NotBlank Libro libro, @NotBlank Persona persona) {
	 
		 
		Fecha_Creacion = fecha_Creacion;
		Descripcion = descripcion;
		Estado = estado;
		this.libro = libro;
		this.persona = persona;
	}


	 


	public String getFecha_Creacion() {
		return Fecha_Creacion;
	}


	public void setFecha_Creacion(String fecha_Creacion) {
		Fecha_Creacion = fecha_Creacion;
	}


	public String getDescripcion() {
		return Descripcion;
	}


	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}


	public int getEstado() {
		return Estado;
	}


	public void setEstado(int estado) {
		Estado = estado;
	}


	public Libro getLibro() {
		return libro;
	}


	public void setLibro(Libro libro) {
		this.libro = libro;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
	
	
	
	
	

}
