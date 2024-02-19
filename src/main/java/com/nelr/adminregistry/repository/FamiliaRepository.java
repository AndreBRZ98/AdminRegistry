package com.nelr.adminregistry.repository;

import com.nelr.adminregistry.entity.Familia;
import com.nelr.adminregistry.entity.FamiliaId;
import com.nelr.adminregistry.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FamiliaRepository extends CrudRepository<Familia, String> {
    @Query("SELECT F.persona FROM Familia F WHERE F.familiaId.familiaId=:familiaId AND F.parentesco = 'HIJO_HIJA'")
    public Iterable<Persona> findHijos(@Param("familiaId") String familiaId);
    public Optional<Familia> findByPersona(Persona persona);


}
