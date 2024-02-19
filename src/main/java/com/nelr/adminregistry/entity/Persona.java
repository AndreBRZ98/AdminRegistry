package com.nelr.adminregistry.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "gelr_persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
	
	@Id
	@Column(name = "id")
	private String personaId;
	private String nombre;
	private String apellidos;
	@Enumerated(EnumType.STRING)
	private Genero genero;
	private LocalDate fechaNacimiento;
	private String correo;
	private String celular;
	private String codigoPostal;
	private String direccion;
	private String ciudad;
	private String estado;
	//@Column(columnDefinition = "JSON")
	//private String familia;
	
	
	public String getPersonaId() {
		return personaId;
	}


	public void setPersonaId(String personaId) {
		this.personaId = personaId;
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


	@Override
	public int hashCode() {
		return Objects.hash(personaId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(personaId, other.personaId);
	}



	@Override
	public String toString() {
		return "Persona [id=" + personaId + ", nombre=" + nombre + ", apellidos=" + apellidos + ", genero=" + genero
				+ ", fechaNacimiento=" + fechaNacimiento + ", correo=" + correo + ", celular=" + celular
				+ ", codigoPostal=" + codigoPostal + ", direccion=" + direccion + ", ciudad=" + ciudad + ", estado="
				+ estado + "]";
	}
	
	
	

}
