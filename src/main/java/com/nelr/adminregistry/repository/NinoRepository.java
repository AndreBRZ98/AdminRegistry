package com.nelr.adminregistry.repository;


import java.time.LocalDate;
import java.util.Optional;

import com.nelr.adminregistry.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nelr.adminregistry.entity.Nino;

public interface NinoRepository extends CrudRepository<Nino, String>{
	public Optional<Nino> findByNombreAndFechaNacimiento (String Nombre, LocalDate fechaNacimiento);
	@Query("SELECT N FROM Nino N WHERE N.personaId = :personaId")
	public Optional<Nino> findByPersona (String personaId);

}
