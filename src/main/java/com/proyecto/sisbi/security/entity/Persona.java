package com.proyecto.sisbi.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
import javax.persistence.Table; 
import javax.validation.constraints.NotNull; 
 

@Entity
@Table(name = "persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID_Persona;
	//@NotNull
	@Column(name = "Dni") 
	private String dni;
	@NotNull
	@Column(name = "Nombre")
	private String Nombre;
	@NotNull
	@Column(name = "Apellidos")
	private String Apellidos;
	@NotNull
	@Column(name = "Direccion")
	private String Direccion;
	@NotNull
	
	@Column(name = "Telefono")
	private String Telefono;
	@NotNull
	
	@Column(name = "CorreoElectronico")
	private String correoElectronico;
	@NotNull
	@Column(name = "Clave")
	private String Clave;
	@NotNull
	@Column(name = "Estado")
	private String Estado;

	 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "persona_rol", joinColumns = @JoinColumn(name = "ID_Persona"), inverseJoinColumns = @JoinColumn(name = "ID_Rol"))

	private Set<Rol> roles = new HashSet<>();
	 
	public Persona() {

	}

	public Persona(String dni, String nombre, String apellidos, String direccion, String telefono,
			String correoElectronico, String clave, String estado) {

		this.dni = dni;
		Nombre = nombre;
		Apellidos = apellidos;
		Direccion = direccion;
		Telefono = telefono;
		this.correoElectronico = correoElectronico;
		Clave = clave;
		Estado = estado;
		
	}

	public int getID_Persona() {
		return ID_Persona;
	}

	public void setID_Persona(int iD_Persona) {
		ID_Persona = iD_Persona;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	 
	
}
