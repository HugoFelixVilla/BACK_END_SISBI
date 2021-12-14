package com.proyecto.sisbi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.sisbi.security.entity.Persona;
import com.proyecto.sisbi.security.entity.PersonaPrincipal; 

@Service
//public class UserDetailsServiceImpl{
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	PersonaService personaservice;

	 

	@Override
	public UserDetails loadUserByUsername(String Dni) throws UsernameNotFoundException {
	   Persona persona = personaservice.getByDNI(Dni).get();
        return PersonaPrincipal.build(persona);
	}
	
	 
	
}
