package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;

// ENTITY - Motorcycle - Vehicle subclass
// Hereda de Vehicle usando estrategia de herencia JOINED
@Entity
@Table(name = "motorcycle")
@PrimaryKeyJoinColumn(name = "id")
public class Motorcycle extends Vehicle {

    // ATRIBUTO - hasSidecar - indica si la moto tiene sidecar
    private boolean hasSidecar;

    // CONSTRUCTOR por defecto para JPA
    public Motorcycle() {
    }

    // GETTER - retorna true si tiene sidecar, false en caso contrario
    public boolean isHasSidecar() {
        return hasSidecar;
    }

    // SETTER - asigna el estado del sidecar
    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }
} 