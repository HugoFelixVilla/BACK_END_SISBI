package com.proyecto.sisbi.dto;
 
import javax.validation.constraints.Email; 
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditorialDto {
	 
	
	@NotBlank
	@Size(min = 10, max = 12)
private String RUC;
	
	@NotBlank
	private String nombre; 
	
	
	private String Descripcion;
	
	@NotBlank
	private String Direccion;
	
	@NotBlank
	@Size(min = 7, max = 9)
	private String Telefono1;
	 
	
	@Size(min = 7, max = 9)
	private String Telefono2;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String FechaCreacion;
	
	 
	private String Estado;

	public EditorialDto() {
		 
	}

	 

	public EditorialDto(@NotBlank @Size(min = 8, max = 8) String rUC, @NotBlank String nombre, String descripcion,
			@NotBlank String direccion, @NotBlank @Size(min = 7, max = 9) String telefono1,
			@Size(min = 7, max = 9) String telefono2, @NotBlank @Email String correoElectronico,
			@NotBlank String fechaCreacion, String estado) {
		 
		RUC = rUC;
		this.nombre = nombre;
		Descripcion = descripcion;
		Direccion = direccion;
		Telefono1 = telefono1;
		Telefono2 = telefono2;
		this.email = correoElectronico;
		FechaCreacion = fechaCreacion;
		Estado = estado;
	}



	public String getRUC() {
		return RUC;
	}

	public void setRUC(String rUC) {
		RUC = rUC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		email = correoElectronico;
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



	public String getDescripcion() {
		return Descripcion;
	}



	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}



	public String getDireccion() {
		return Direccion;
	}



	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
	
	
	
	
}
