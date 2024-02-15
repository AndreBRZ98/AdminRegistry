package com.nelr.adminregistry.repository;

import com.nelr.adminregistry.entity.Nino;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nelr.adminregistry.entity.Persona;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface PersonaRepository extends CrudRepository<Persona, String> {
    public Optional<Persona> findByCorreo (String correo);

    @Query("SELECT n FROM Persona n WHERE FUNCTION('JSON_EXTRACT', n.familia, '$.padreId') = :personaId")
    public  Iterable<Persona> findByFamiliaJSON(@Param("personaId") String personaId);
}
