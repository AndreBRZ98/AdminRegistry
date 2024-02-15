package com.nelr.adminregistry.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nelr.adminregistry.entity.Login;

public interface LoginRepository extends CrudRepository<Login, String>{
	
	public Optional<Login> findByCorreo(String correo);
}
