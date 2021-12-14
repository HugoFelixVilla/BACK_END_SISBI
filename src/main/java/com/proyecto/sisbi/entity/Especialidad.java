package com.proyecto.sisbi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="especialidad")
public class Especialidad implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 
private Integer ID_ESPECIALIDAD;
	
	@Column(name="Nombre")
private String nombre;
	
	@Column(name="Descripcion")
	private String Descripcion;
	
	@Column(name="Estado")
	private String Estado;

	public Especialidad() {
		 
	}

	public Especialidad(String nombre, String descripcion, String estado) {
	 
		this.nombre = nombre;
		Descripcion = descripcion;
		Estado = estado;
	}

	public Integer getID_Especialidad() {
		return ID_ESPECIALIDAD;
	}

	public void setID_Especialidad(Integer idespe) {
		this.ID_ESPECIALIDAD = idespe;
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
