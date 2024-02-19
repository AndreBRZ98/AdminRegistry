package com.nelr.adminregistry.dto;

import com.nelr.adminregistry.entity.Servidor;

public class ServidorDTO extends PersonaDTO{
	
    private String nivelIbc;
    private boolean bautizo;
    private String notas;
    
	public String getNivelIbc() {
		return nivelIbc;
	}
	public void setNivelIbc(String nivelIbc) {
		this.nivelIbc = nivelIbc;
	}
	public boolean isBautizo() {
		return bautizo;
	}
	public void setBautizo(boolean bautizo) {
		this.bautizo = bautizo;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
    
	public void createPersonaDTO(Servidor servidor) {
		
		super.setApellidos(servidor.getApellidos());
		super.setCelular(servidor.getApellidos());
		super.setCiudad(servidor.getCiudad());
		super.setCodigoPostal(servidor.getCodigoPostal());
		super.setCorreo(servidor.getCorreo());
		super.setDireccion(servidor.getDireccion());
		super.setEstado(servidor.getEstado());
		super.setFechaNacimiento(servidor.getFechaNacimiento());
		super.setGenero(servidor.getGenero());
		super.setNombre(servidor.getNombre());
		super.setPersonaId(servidor.getPersonaId());
		
		this.bautizo=servidor.isBautizo();
		this.nivelIbc=servidor.getNivelIbc();
		this.notas=servidor.getNotas();

		
	}
	
	public Servidor createServidorEntity() {
		
		Servidor servidor = new Servidor();
		servidor.setApellidos(super.getApellidos());
		servidor.setCelular(super.getCelular());
		servidor.setCiudad(super.getCiudad());
		servidor.setCodigoPostal(super.getCodigoPostal());
		servidor.setCorreo(super.getCorreo());
		servidor.setDireccion(super.getDireccion());
		servidor.setEstado(super.getEstado());
		servidor.setFechaNacimiento(super.getFechaNacimiento());
		servidor.setGenero(super.getGenero());
		servidor.setNombre(super.getNombre());
		servidor.setPersonaId(super.getPersonaId());
		
		servidor.setBautizo(this.bautizo);
		servidor.setNivelIbc(this.nivelIbc);
		servidor.setNotas(this.notas);
		
		return servidor;
	}
	
    
    
    

}
