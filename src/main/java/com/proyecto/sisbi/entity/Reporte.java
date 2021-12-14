package com.proyecto.sisbi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.proyecto.sisbi.security.entity.Persona;
 

@Entity
@Table(name="reporte")
public class Reporte {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_REPORTE;
	
 
	
	@Column(name="Fecha_Creacion")
	private String Fecha_Creacion;
	
	@Column(name="Descripcion")
	private String Descripcion;
	
	 
	@Column(name="Estado")
	private int Estado;
	
	
	@ManyToOne
	 @JoinColumn(name="ID_LIBRO")
	 private Libro libro;
	
	@ManyToOne
	 @JoinColumn(name="ID_PERSONA")
	 private Persona persona;

	public Reporte() {
		 
	}

	public Reporte(String fecha_Creacion, String descripcion, int estado, Libro libro,
			Persona persona) {
		 
	 
		Fecha_Creacion = fecha_Creacion;
		Descripcion = descripcion;
		Estado = estado;
		this.libro = libro;
		this.persona = persona;
	}

	public Integer getID_REPORTE() {
		return ID_REPORTE;
	}

	public void setID_REPORTE(Integer iD_REPORTE) {
		ID_REPORTE = iD_REPORTE;
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
