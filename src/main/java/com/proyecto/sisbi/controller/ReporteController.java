package com.proyecto.sisbi.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 
import com.proyecto.sisbi.dto.Mensaje;
import com.proyecto.sisbi.dto.ReporteDto; 
import com.proyecto.sisbi.entity.Reporte;
import com.proyecto.sisbi.service.ReporteService; 

@RestController
@RequestMapping("/reporte")
@CrossOrigin(origins = "http://localhost:8081")
public class ReporteController {
	
	
	@Autowired
	ReporteService reporteService;

	@GetMapping("/lista")
	public ResponseEntity<List<Reporte>> list() {
		List<Reporte> list = reporteService.list();
		// return new ResponseEntity(list,HttpStatus.OK);

		return new ResponseEntity<List<Reporte>>(list, HttpStatus.OK);
	}

	@GetMapping("/datail/{id}")
	public ResponseEntity<Reporte> getById(@PathVariable("id") int id) {
		if (!reporteService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Reporte reporte = reporteService.getOne(id).get();
		return new ResponseEntity(reporte, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ReporteDto reporteDto) {
		

		 if (StringUtils.isBlank(reporteDto.getDescripcion()))
			return new ResponseEntity(new Mensaje("la descripción es obligatorio"), HttpStatus.BAD_REQUEST);
		 
			if (reporteDto.getDescripcion().matches(("^[0-9]$")))
				return new ResponseEntity(new Mensaje("la descripción debe ser solo letras"), HttpStatus.BAD_REQUEST);


			Reporte reporte = new Reporte(reporteDto.getFecha_Creacion(),reporteDto.getDescripcion(),reporteDto.getEstado(),reporteDto.getLibro(), reporteDto.getPersona());

			reporteService.save(reporte);
			return new ResponseEntity(new Mensaje("reporte creado"), HttpStatus.OK);
	
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ReporteDto reporteDto,
			BindingResult bindingResult) {
		
		if (!reporteService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);


			 
		 if (StringUtils.isBlank(reporteDto.getDescripcion()))
				return new ResponseEntity(new Mensaje("la descripción es obligatorio"), HttpStatus.BAD_REQUEST);
			 
				if (reporteDto.getDescripcion().matches(("^[0-9]$")))
					return new ResponseEntity(new Mensaje("la descripción debe ser solo letras"), HttpStatus.BAD_REQUEST);

 
		 
		Reporte reporte = reporteService.getOne(id).get();
		reporte.setLibro(reporteDto.getLibro());
		reporte.setPersona(reporteDto.getPersona());
		reporte.setFecha_Creacion(reporteDto.getFecha_Creacion());
		reporte.setDescripcion(reporteDto.getDescripcion()); 
		reporte.setEstado(reporteDto.getEstado());
		 
	 
		reporteService.save(reporte);

		return new ResponseEntity(new Mensaje("reporte actualizado"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!reporteService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);

		reporteService.delete(id);
		return new ResponseEntity(new Mensaje("reporte eliminado"), HttpStatus.OK);
	}

}
	


