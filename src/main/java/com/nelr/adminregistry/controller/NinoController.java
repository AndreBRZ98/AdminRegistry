package com.nelr.adminregistry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelr.adminregistry.dto.NinoDTO;
import com.nelr.adminregistry.exception.NinoException;
import com.nelr.adminregistry.service.NinoService;

@RestController
@RequestMapping(value ="/nelr")
public class NinoController {

	@Autowired
	private NinoService ninoService;

	@GetMapping(value ="/nino")
	public ResponseEntity<List<NinoDTO>> getNinosByPersona() throws NinoException{
			List<NinoDTO> ninoDTOs = ninoService.getNinosByPersona();
			return new ResponseEntity<>(ninoDTOs, HttpStatus.OK);
	}
	
	@PostMapping(value ="/nino")
	public ResponseEntity<String> addNino(@RequestBody NinoDTO ninoDTO) throws NinoException, Exception{
		String successMessage = ninoService.addNino(ninoDTO);
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}
