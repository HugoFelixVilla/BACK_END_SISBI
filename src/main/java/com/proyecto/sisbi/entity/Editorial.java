package com.proyecto.sisbi.entity;
 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="editorial")
public class Editorial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_EDITORIAL;
	
	@Column(name="ruc")
private String ruc;
	
	@Column(name="Nombre")
	private String nombre;
	
	@Column(name="Descripcion")
	private String Descripcion;
	
	
	@Column(name="Direccion")
	private String Direccion;
	
	@Column(name="Telefono1")
	private String Telefono1;
	
	@Column(name="Telefono2")
	private String Telefono2;
	
	@Column(name="CorreoElectronico")
	private String email;
	
	@Column(name="FechaCreacion")
	private String FechaCreacion;
	
	@Column(name="Estado")
	private String Estado;

	public Editorial() {
		 
	}

	 

	public Editorial(String rUC, String nombre, String descripcion, String direccion, String telefono1,
			String telefono2, String correoElectronico, String fechaCreacion, String estado) {
		 
		this.ruc = rUC;
		this.nombre = nombre;
		Descripcion = descripcion;
		Direccion = direccion;
		Telefono1 = telefono1;
		Telefono2 = telefono2;
		this.email = correoElectronico;
		FechaCreacion = fechaCreacion;
		Estado = estado;
	}



	public String getDireccion() {
		return Direccion;
	}



	public void setDireccion(String direccion) {
		Direccion = direccion;
	}



 

	public Integer getID_EDITORIAL() {
		return ID_EDITORIAL;
	}



	public void setID_EDITORIAL(Integer iD_EDITORIAL) {
		ID_EDITORIAL = iD_EDITORIAL;
	}



	public String getRUC() {
		return ruc;
	}

	public void setRUC(String rUC) {
		this.ruc = rUC;
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

	public String getTelefono1() {
		return Telefono1;
	}

	public void setTelefono1(String telefono1) {
		Telefono1 = telefono1;
	}

	public String getTelefono2() {
		return Telefono2;
	}

	public void setTelefono2(String telefono2) {
		Telefono2 = telefono2;
	}

	public String getCorreoElectronico() {
		return email;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.email = correoElectronico;
	}

	public String getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
	
	 
}
