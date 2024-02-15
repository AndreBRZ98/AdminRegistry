package com.nelr.adminregistry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelr.adminregistry.dto.FamiliaDTO;
import com.nelr.adminregistry.dto.NinoDTO;
import com.nelr.adminregistry.entity.Nino;
import com.nelr.adminregistry.entity.Persona;
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
	ObjectMapper objectMapper;

	
	


	@Override
	public List<NinoDTO> getAllNinos() throws NinoException {
		/*Iterable<Nino> ninosIterable = ninoRepository.findAll();
		List<NinoDTO> ninoDTOs = new ArrayList<>();
		
		ninosIterable.forEach(nino -> {
			NinoDTO ninoDTO = new NinoDTO();
			ninoDTO.createNinoDTO(nino);
			ninoDTOs.add(ninoDTO);
		});
		if(ninoDTOs.isEmpty()) {
			throw new NinoException("No se encontraron ninos en la base de datos");
		}
		return ninoDTOs;*/
		
			// coreo del usurario logeado
            //String correo =  authInformation.getName();
       
  
		return null;
	}

	@Override
	public String addNino(NinoDTO ninoDTO) throws NinoException, Exception {
		//Busca el ID del padre
		Authentication authInformation = SecurityContextHolder.getContext().getAuthentication();
		Optional<Persona> personaPadreOptional = personaRepository.findByCorreo(authInformation.getName());
		Persona personaPadre = personaPadreOptional.orElseThrow(() -> new NinoException("Algo salio mal"));
		//Revisa si el nino ya existe
		if(ninoRepository.findByNombreAndFechaNacimiento(ninoDTO.getNombre(), ninoDTO.getFechaNacimiento()).isPresent()) {
			throw new NinoException(ninoDTO.getNombre()+" ya existe en el sistema.");
		}
		//Se crea la entidad del nino
		Nino ninoEntity = ninoDTO.createNinoEntity();
		ninoEntity.setPersonaId(KeyGenerator.getPassword());
		ninoEntity.setId(KeyGenerator.getPassword());
		//Se obtiene familia JSON del padre
		String familiaJSON = personaPadre.getFamilia();
		//Se convierte JSON a objeto JAVA y se agrega el nuevo hijo
		FamiliaDTO familia = objectMapper.readValue(familiaJSON, FamiliaDTO.class);
		List<String> hijos = familia.getHijos();
		hijos.add(ninoEntity.getId());
		familia.setHijos(hijos);
		//Se convierte el ojeto JAVA a JSON nuevamente
		familiaJSON=objectMapper.writeValueAsString(familia);
		//Se traen todos los miembros de la familia para actualizar el familiaJSON
		Iterable<Persona> personasFamilia = personaRepository.findByFamiliaJSON(personaPadre.getPersonaId());
		String finalFamiliaJSON = familiaJSON;
		personasFamilia.forEach(persona -> {
			persona.setFamilia(finalFamiliaJSON);
		});
		//Se guarda el familia JSON en la entidad del nuevo nino
		ninoEntity.setFamilia(finalFamiliaJSON);
		//Se guarda la entidad del nino
		String ninoId = ninoRepository.save(ninoEntity).getId();

		return ninoDTO.getNombre() + "se agrego correctamente";
	}

	@Override
	public List<NinoDTO> getNinosByPersona() throws NinoException {
		//Busca el ID del padre
		Authentication authInformation = SecurityContextHolder.getContext().getAuthentication();
		Optional<Persona> personaPadreOptional = personaRepository.findByCorreo(authInformation.getName());
		Persona personaPadre = personaPadreOptional.orElseThrow(() -> new NinoException("Algo salio mal"));

		Iterable<Nino> ninoIterable = ninoRepository.getNinosByPersona(personaPadre.getPersonaId());
		List<NinoDTO> ninoDTOs = new ArrayList<>();
		ninoIterable.forEach(nino -> {
			NinoDTO ninoDTO = new NinoDTO();
			ninoDTO.createNinoDTO(nino);
			ninoDTOs.add(ninoDTO);
		});
		if(ninoDTOs.isEmpty()) {
			throw new NinoException("Aun no has agregado a tus niños");
		}
		return ninoDTOs;
	}

}
