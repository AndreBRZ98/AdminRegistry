package com.nelr.adminregistry.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "gelr_familia")
public class Familia {

    @ManyToOne
    @JoinColumn(name = "persona_id", insertable = false, updatable = false)
    private Persona persona;
    @EmbeddedId
    private FamiliaId familiaId;
    @Enumerated(EnumType.STRING)
    private Parentesco parentesco;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public FamiliaId getFamiliaId() {
        return familiaId;
    }

    public void setFamiliaId(FamiliaId familiaId) {
        this.familiaId = familiaId;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }
}
