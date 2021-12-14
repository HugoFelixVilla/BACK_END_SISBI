package com.proyecto.sisbi.security.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.proyecto.sisbi.security.enums.RolNombre;

@Entity
@Table(name="rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer ID_Rol;
	 
	@NotNull
	@Column(name="Nombre")
	@Enumerated(EnumType.STRING)
	private RolNombre rolNombre;
	 
	
//	@Column(name="Estado")
//	private String Estado;


	public Rol() {
		 
	}


	 
 


	public Rol(@NotNull RolNombre rolNombre) {
		 
		this.rolNombre = rolNombre;
		
	}

 


	public Integer getID_Rol() {
		return ID_Rol;
	}





	public void setID_Rol(Integer iD_Rol) {
		ID_Rol = iD_Rol;
	}





	public RolNombre getRolNombre() {
		return rolNombre;
	}





	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}





//	public String getEstado() {
//		return Estado;
//	}
//
//
//
//
//
//	public void setEstado(String estado) {
//		Estado = estado;
//	}





 	
	
	
	
	
}
