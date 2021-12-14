package com.proyecto.sisbi.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.proyecto.sisbi.entity.Editorial;
import com.proyecto.sisbi.entity.Especialidad;

public class LibroDto {
	@NotBlank
	private String nombre;
	
	@NotBlank
	@Size(min = 4, max = 4)
	private String AnioPublicacion;
	
	@NotBlank
	private String NroPaginas;
	
	@NotBlank
	private String Estado;
	
	@ManyToOne
	 @JoinColumn(name="ID_ESPECIALIDAD")
	 private Especialidad especialidad;
	
	@ManyToOne
	 @JoinColumn(name="ID_EDITORIAL")
	 private Editorial editorial;

	public LibroDto() {
		 
	}

 

	public LibroDto(@NotBlank String nombre, @NotBlank @Size(min = 4, max = 4) String anioPublicacion,
			@NotBlank String nroPaginas, @NotBlank String estado, Especialidad especialidad, Editorial editorial) {
		super();
		this.nombre = nombre;
		AnioPublicacion = anioPublicacion;
		NroPaginas = nroPaginas;
		Estado = estado;
		this.especialidad = especialidad;
		this.editorial = editorial;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAnioPublicacion() {
		return AnioPublicacion;
	}

	public void setAnioPublicacion(String anioPublicacion) {
		AnioPublicacion = anioPublicacion;
	}

	public String getNroPaginas() {
		return NroPaginas;
	}

	public void setNroPaginas(String nroPaginas) {
		NroPaginas = nroPaginas;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}



	public Especialidad getEspecialidad() {
		return especialidad;
	}



	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}



	public Editorial getEditorial() {
		return editorial;
	}



	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	 
	
	
	

}
