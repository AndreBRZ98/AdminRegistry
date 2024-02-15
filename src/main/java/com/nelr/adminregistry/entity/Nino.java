package com.nelr.adminregistry.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "nelr_nino")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Nino extends Persona{
	
	private String id;
	private String alergias;
	private String notas;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	
}
