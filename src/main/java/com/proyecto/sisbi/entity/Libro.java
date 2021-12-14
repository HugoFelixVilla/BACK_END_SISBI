package com.proyecto.sisbi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 

@Entity
@Table(name="libro")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_LIBRO;
	
	@Column(name="Nombre")
private String nombre;
	
	@Column(name="AnioPublicacion")
	private String AnioPublicacion;
	
	@Column(name="NroPaginas")
	private String NroPaginas;
	
	 
	@Column(name="Estado")
	private String Estado;
	 
	
	@ManyToOne
	 @JoinColumn(name="ID_ESPECIALIDAD")
	 private Especialidad especialidad;
	
	@ManyToOne
	 @JoinColumn(name="ID_EDITORIAL")
	 private Editorial editorial;
	
	

	public Libro() {
		 
	}



	public Libro(String nombre, String anioPublicacion, String nroPaginas, String estado, Especialidad especialidad,
			Editorial editorial) {
		 
		this.nombre = nombre;
		AnioPublicacion = anioPublicacion;
		NroPaginas = nroPaginas;
		Estado = estado;
		this.especialidad = especialidad;
		this.editorial = editorial;
	}



	public Integer getID_LIBRO() {
		return ID_LIBRO;
	}



	public void setID_LIBRO(Integer iD_LIBRO) {
		ID_LIBRO = iD_LIBRO;
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
