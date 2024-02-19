package com.nelr.adminregistry.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class FamiliaId implements Serializable {

    @Column(name = "persona_id")
    private String personaId;
    @Column(name = "familia_id")
    private String familiaId;

    public FamiliaId(String personaId, String familiaId) {
        this.personaId = personaId;
        this.familiaId = familiaId;
    }
    public FamiliaId() {
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(String familiaId) {
        this.familiaId = familiaId;
    }


}
