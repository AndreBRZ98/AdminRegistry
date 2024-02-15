package com.nelr.adminregistry.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gelr_login")
public class Login implements UserDetails{
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "id")
	private String loginId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "persona_id", unique = true)
	private Persona persona;
	private String correo;
	private String contrasena;
	private String rolId;
	
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public void setUsername(String correo) { //se cambia nombre porque implementa UserDetails
		this.correo = correo;
	}
	public void setPassword(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getRolId() {
		return rolId;
	}
	public void setRolId(String rolId) {
		this.rolId = rolId;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority((getRolId()))); //necesario definr roles
	}
	@Override
	public String getPassword() {
		
		return contrasena;
	}
	@Override
	public String getUsername() {

		return correo;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true; //se pone true porque se trabaja con jwt
	}
	@Override
	public boolean isAccountNonLocked() {
		return true; //se pone true porque se trabaja con jwt
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true; //se pone true porque se trabaja con jwt
	}
	@Override
	public boolean isEnabled() {
		return true; //se pone true porque se trabaja con jwt
	}

	
	

}
