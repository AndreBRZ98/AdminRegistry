package com.nelr.adminregistry.dto;

import java.time.LocalDate;

import com.nelr.adminregistry.entity.Genero;
import com.nelr.adminregistry.entity.Persona;


public class PersonaDTO {
	
	private String personaId;
	private String nombre;
	private String apellidos;
	private Genero genero;
	private LocalDate fechaNacimiento;
	private String correo;
	private String celular;
	private String codigoPostal;
	private String direccion;
	private String ciudad;
	private String estado;
	
	public String getPersonaId() {
		return personaId;
	}
	public void setPersonaId(String id) {
		this.personaId = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	public void createPersonaDTO(Persona persona) {
		this.apellidos=persona.getApellidos();
		this.celular=persona.getCelular();
		this.ciudad=persona.getCiudad();
		this.codigoPostal=persona.getCodigoPostal();
		this.correo=persona.getCorreo();
		this.direccion=persona.getDireccion();
		this.estado=persona.getEstado();
		this.fechaNacimiento=persona.getFechaNacimiento();
		this.genero=persona.getGenero();
		this.personaId=persona.getPersonaId();
		this.nombre=persona.getNombre();
	}
	
	public Persona createPersonaEntity() {
		Persona persona = new Persona();
		persona.setApellidos(this.apellidos);
		persona.setCelular(this.celular);
		persona.setCiudad(this.ciudad);
		persona.setCodigoPostal(this.codigoPostal);
		persona.setCorreo(this.correo);
		persona.setDireccion(this.direccion);
		persona.setEstado(this.estado);
		persona.setFechaNacimiento(this.fechaNacimiento);
		persona.setGenero(this.genero);
		persona.setPersonaId(this.personaId);
		persona.setNombre(this.nombre);
		return persona;
		
	}
}
