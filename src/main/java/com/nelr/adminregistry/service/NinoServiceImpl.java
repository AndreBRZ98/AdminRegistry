package com.nelr.adminregistry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nelr.adminregistry.entity.*;
import com.nelr.adminregistry.repository.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelr.adminregistry.dto.FamiliaDTO;
import com.nelr.adminregistry.dto.NinoDTO;
import com.nelr.adminregistry.exception.NinoException;
import com.nelr.adminregistry.jwt.JwtService;
import com.nelr.adminregistry.repository.NinoRepository;
import com.nelr.adminregistry.repository.PersonaRepository;
import com.nelr.adminregistry.utility.KeyGenerator;

import jakarta.transaction.Transactional;

@Transactional
@Service(value = "ninoService")
public class NinoServiceImpl implements NinoService {
	
	@Autowired
	NinoRepository ninoRepository;
	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	FamiliaRepository familiaRepository;
	@Autowired
	ObjectMapper objectMapper;


	@Override
	public String addNino(NinoDTO ninoDTO) throws NinoException, Exception {
		//Traemos la entidad de persona y familia correspondiente al PADRE_MADRE
		//Utilizamos la informacion del SecurityContextHolder para conocer el correo del PADRE_MADRE
		Authentication authInformation = SecurityContextHolder.getContext().getAuthentication();
		Optional<Persona> personaPadreMadreOptional = personaRepository.findByCorreo(authInformation.getName());

		Persona personaPadreMadre = personaPadreMadreOptional.orElseThrow(() -> new NinoException("Algo salio mal"));
		Optional<Familia> familiaOptional = familiaRepository.findByPersona(personaPadreMadre);
		Familia familia = familiaOptional.orElseThrow(() -> new NinoException("Algo salio mal"));

		//Revisa si el nino ya existe, MEJORAR ESTA VALIDACION
		if(ninoRepository.findByNombreAndFechaNacimiento(ninoDTO.getNombre(), ninoDTO.getFechaNacimiento()).isPresent()) {
			throw new NinoException(ninoDTO.getNombre()+" ya existe en el sistema.");
		}
		//Se crea la entidad del nino
		Nino ninoEntity = ninoDTO.createNinoEntity();
		ninoEntity.setPersonaId(KeyGenerator.getPassword());
		ninoEntity.setId(KeyGenerator.getPassword());

		//Se crea el registro que corresponde al nino en la familia
		Familia familiaNino = new Familia();
		familiaNino.setFamiliaId(new FamiliaId(ninoEntity.getPersonaId(), familia.getFamiliaId().getFamiliaId()));
		familiaNino.setPersona(ninoEntity);
		familiaNino.setParentesco(Parentesco.HIJO_HIJA);

		//Se guarda la entidad del nino y el nuevo registro en la tabla familia
		String ninoNombre = ninoRepository.save(ninoEntity).getNombre();
		familiaRepository.save(familiaNino);

		return ninoNombre + " se agrego correctamente";
	}

	@Override
	public List<NinoDTO> getNinosByPersona() throws NinoException {

		Authentication authInformation = SecurityContextHolder.getContext().getAuthentication();
		Optional<Persona> personaPadreOptional = personaRepository.findByCorreo(authInformation.getName());
		Persona personaPadre = personaPadreOptional.orElseThrow(() -> new NinoException("Algo salio mal"));

		// Buscamos el registro de familia del PADRE_MADRE
		Optional<Familia> familiaPadreOptional = familiaRepository.findByPersona(personaPadre);
		Familia familiaPadre = familiaPadreOptional.orElseThrow(() -> new NinoException("Algo salio mal"));

		//Traemos a los hijos
		Iterable<Persona> hijosIterable = familiaRepository.findHijos(familiaPadre.getFamiliaId().getFamiliaId());
		List<NinoDTO> hijosDTOs = new ArrayList<>();
		hijosIterable.forEach(personaHijo -> {
			//Buscamos el objeto nino a segun su personaId
			Optional<Nino> personaHijoNinoOptional =  ninoRepository.findByPersona(personaHijo.getPersonaId());
			Nino personaHijoNino = personaHijoNinoOptional.orElseThrow();
			NinoDTO hijoDTO = new NinoDTO();
			hijoDTO.createNinoDTO(personaHijoNino);
			hijosDTOs.add(hijoDTO);
		});

		if(hijosDTOs.isEmpty()) {
			throw new NinoException("Aun no has agregado a tus niños");
		}

		return hijosDTOs;
	}

}
