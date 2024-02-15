package com.nelr.adminregistry.repository;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nelr.adminregistry.entity.Nino;

public interface NinoRepository extends CrudRepository<Nino, String>{
	
	@Query("SELECT n FROM Nino n WHERE FUNCTION('JSON_EXTRACT', n.familia, '$.padreId') = :personaId OR FUNCTION('JSON_EXTRACT', n.familia, '$.madreId') = :personaId")
	public Iterable<Nino> getNinosByPersona(@Param("personaId") String personaId);
	
	public Optional<Nino> findByNombreAndFechaNacimiento (String Nombre, LocalDate fechaNacimiento);
}




//"SELECT p, n FROM Nino n INNER JOIN Persona p ON n.id = p.personaId WHERE FUNCTION('JSON_EXTRACT', p.familia, '$.padreId') = :personaId"



/*"SELECT "
+ "g.id AS personaId ,g.nombre, g.apellidos, g.genero, g.fecha_nacimiento AS fechaNacimiento, g.correo, g.celular, g.codigo_postal AS codigoPostal, g.direccion, g.ciudad, g.estado, g.familia,"
+ "n.id AS ninoId, n.alergias, n.notas"
+ "FROM nelr_nino n "
+ "INNER JOIN gelr_persona g "
+ "ON n.persona_id = g.id "
+ "WHERE FUNCTION('JSON_EXTRACT', g.familia, '$.padreId') = :personaId"*/