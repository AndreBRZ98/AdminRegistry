package com.nelr.adminregistry.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "gelr_servidor")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Servidor extends Persona {
	
	private String id;
    private String nivelIbc;
    private boolean bautizo;
    private String notas;
    

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}  
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
  
    
}
