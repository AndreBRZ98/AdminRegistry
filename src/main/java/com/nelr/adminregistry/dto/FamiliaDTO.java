package com.nelr.adminregistry.dto;

import java.util.List;

public class FamiliaDTO {
	
	private String padreId;
	private String madreId;
	private String tutor1Id;
	private String tutor2Id;
	private List<String> hijos;
	
	
	public String getPadreId() {
		return padreId;
	}
	public void setPadreId(String padreId) {
		this.padreId = padreId;
	}
	public String getMadreId() {
		return madreId;
	}
	public void setMadreId(String madreId) {
		this.madreId = madreId;
	}
	public String getTutor1Id() {
		return tutor1Id;
	}
	public void setTutor1Id(String tutor1Id) {
		this.tutor1Id = tutor1Id;
	}
	public String getTutor2Id() {
		return tutor2Id;
	}
	public void setTutor2Id(String tutor2Id) {
		this.tutor2Id = tutor2Id;
	}
	public List<String> getHijos() {
		return hijos;
	}
	public void setHijos(List<String> hijos) {
		this.hijos = hijos;
	}
	
	

}
