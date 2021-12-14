package com.proyecto.sisbi.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
	private String token;
	private String bearer="Bearer";
	private String Dni;
	private Collection<? extends GrantedAuthority> authorities;
	public JwtDto(String token, String dni, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		Dni = dni;
		this.authorities = authorities;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getBearer() {
		return bearer;
	}
	public void setBearer(String bearer) {
		this.bearer = bearer;
	}
	public String getDni() {
		return Dni;
	}
	public void setDni(String dni) {
		Dni = dni;
	}
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
	 
}
