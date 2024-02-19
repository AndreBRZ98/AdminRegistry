package com.nelr.adminregistry.auth;

import java.util.ArrayList;
import java.util.List;

import com.nelr.adminregistry.entity.*;
import com.nelr.adminregistry.repository.FamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nelr.adminregistry.dto.FamiliaDTO;
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
	private FamiliaRepository familiaRepository;
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
		Familia familiaEntity = new Familia();
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

		familiaEntity.setParentesco(Parentesco.PADRE_MADRE);
		familiaEntity.setFamiliaId(new FamiliaId(personaEntity.getPersonaId(), KeyGenerator.getPassword()));
		familiaEntity.setPersona(personaEntity);

		loginEntity.setLoginId(KeyGenerator.getPassword());
		loginEntity.setPersona(personaEntity);
		loginEntity.setUsername(request.getCorreo());
		loginEntity.setPassword(passwordEncoder.encode(request.getContrasena()));
		loginEntity.setRolId(request.getRolId());
		
		loginRepository.save(loginEntity);
		familiaRepository.save(familiaEntity);

		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setToken(jwtService.getToken(loginEntity));
		
		return authResponse;
		
	}

	
}
