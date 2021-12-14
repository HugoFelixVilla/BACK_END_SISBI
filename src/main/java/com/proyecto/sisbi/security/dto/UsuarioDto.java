package com.proyecto.sisbi.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDto {
	@NotBlank
	@Size(min = 7, max = 9)
	private String Dni;
	@NotBlank
	private String Nombre;
	@NotBlank
	private String Apellidos;
	@NotBlank
	private String Direccion;
	@NotBlank
	@Size(min = 7, max = 9)
	private String Telefono;
	@NotBlank
	@Email
	private String CorreoElectronico;
	@NotBlank
	private String Clave;
	@NotBlank
	private String Estado;
	public UsuarioDto(@NotBlank @Size(min = 7, max = 9) String dni, @NotBlank String nombre, @NotBlank String apellidos,
			@NotBlank String direccion, @NotBlank @Size(min = 7, max = 9) String telefono,
			@NotBlank @Email String correoElectronico, @NotBlank String clave, @NotBlank String estado) {
		super();
		Dni = dni;
		Nombre = nombre;
		Apellidos = apellidos;
		Direccion = direccion;
		Telefono = telefono;
		CorreoElectronico = correoElectronico;
		Clave = clave;
		Estado = estado;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
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
		return CorreoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		CorreoElectronico = correoElectronico;
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
	
	
}
