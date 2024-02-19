package com.nelr.adminregistry.dto;

import com.nelr.adminregistry.entity.Nino;

public class NinoDTO extends PersonaDTO{
	
	private String ninoId;
	private String alergias;
	private String notas;
	
	public String getNinoId() {
		return ninoId;
	}
	public void setNinoId(String ninoId) {
		this.ninoId = ninoId;
	}
	public String getAlergias() {
		return alergias;
	}
	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	
	public void createNinoDTO(Nino nino) {
		
		super.setApellidos(nino.getApellidos());
		super.setCelular(nino.getCelular());
		super.setCiudad(nino.getCiudad());
		super.setCodigoPostal(nino.getCodigoPostal());
		super.setCorreo(nino.getCorreo());
		super.setDireccion(nino.getDireccion());
		super.setEstado(nino.getEstado());
		super.setFechaNacimiento(nino.getFechaNacimiento());
		super.setGenero(nino.getGenero());
		super.setNombre(nino.getNombre());
		super.setPersonaId(nino.getPersonaId());
		
		this.setNinoId(nino.getId());
		this.setAlergias(nino.getAlergias());
		this.setNotas(nino.getNotas());		
	}
	
	public Nino createNinoEntity() {
		
		Nino nino = new Nino();
		nino.setApellidos(super.getApellidos());
		nino.setCelular(super.getCelular());
		nino.setCiudad(super.getCiudad());
		nino.setCodigoPostal(super.getCodigoPostal());
		nino.setCorreo(super.getCorreo());
		nino.setDireccion(super.getDireccion());
		nino.setEstado(super.getEstado());
		nino.setFechaNacimiento(super.getFechaNacimiento());
		nino.setGenero(super.getGenero());
		nino.setNombre(super.getNombre());
		nino.setPersonaId(super.getPersonaId());
		
		nino.setId(this.ninoId); 
		nino.setAlergias(this.alergias);
		nino.setNotas(this.notas);
		
		return nino;
	}
	
}
