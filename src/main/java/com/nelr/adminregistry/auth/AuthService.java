package com.nelr.adminregistry.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelr.adminregistry.dto.FamiliaDTO;
import com.nelr.adminregistry.entity.Login;
import com.nelr.adminregistry.entity.Persona;
import com.nelr.adminregistry.jwt.JwtService;
import com.nelr.adminregistry.repository.LoginRepository;
import com.nelr.adminregistry.utility.KeyGenerator;

import com.google.gson.Gson;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthService {
	
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthResponse login(LoginRequest request) {
		
		String username = request.getCorreo();
		String password = request.getContrasena();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);

		authenticationManager.authenticate(authentication);
		
		UserDetails login = loginRepository.findByCorreo(request.getCorreo()).orElseThrow();
		String token = jwtService.getToken(login); 
		AuthResponse authResponse = new AuthResponse();
		authResponse.setToken(token);
		
		return authResponse;
	}
	
	public AuthResponse register(RegisterRequest request) {
		
		Login loginEntity = new Login();
		Persona personaEntity = new Persona();
		
		personaEntity.setPersonaId(KeyGenerator.getPassword());
		personaEntity.setNombre(request.getNombre());
		personaEntity.setApellidos(request.getApellidos());
		personaEntity.setGenero(request.getGenero());
		personaEntity.setFechaNacimiento(request.getFechaNacimiento());
		personaEntity.setCorreo(request.getCorreo());
		personaEntity.setCelular(request.getCelular());
		personaEntity.setCodigoPostal(request.getCodigoPostal());
		personaEntity.setDireccion(request.getDireccion());
		personaEntity.setCiudad(request.getCiudad());
		personaEntity.setEstado(request.getEstado());
		FamiliaDTO familiaDTO = new FamiliaDTO();
		List<String> ninos = new ArrayList<>();
		if(personaEntity.getGenero().name().equals("M")){
			familiaDTO.setPadreId(personaEntity.getPersonaId());
			familiaDTO.setMadreId("");
		}
		else if (personaEntity.getGenero().name().equals("F")){
			familiaDTO.setPadreId("");
			familiaDTO.setMadreId(personaEntity.getPersonaId());
		}
		familiaDTO.setTutor1Id("");
		familiaDTO.setTutor2Id("");
		familiaDTO.setHijos(ninos);
		Gson gson = new Gson();
		String familia = gson.toJson(familiaDTO);
		personaEntity.setFamilia(familia);
		loginEntity.setLoginId(KeyGenerator.getPassword());
		loginEntity.setPersona(personaEntity);
		loginEntity.setUsername(request.getCorreo());
		loginEntity.setPassword(passwordEncoder.encode(request.getContrasena()));
		loginEntity.setRolId(request.getRolId());
		
		loginRepository.save(loginEntity);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setToken(jwtService.getToken(loginEntity));
		
		return authResponse;
		
	}

	
}
