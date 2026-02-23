package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;

// ENTITY - Plane - Vehicle subclass
// Hereda de Vehicle usando estrategia de herencia JOINED
@Entity
@Table(name = "plane")
@PrimaryKeyJoinColumn(name = "id")
public class Plane extends Vehicle {

    // ATRIBUTO - tailNumber - Número de cola del avión para identificación
    private Integer tailNumber;
    // ATRIBUTO - autopilot - Indica si el avión tiene piloto automático
    private boolean autopilot;

    // CONSTRUCTOR por defecto para JPA
    public Plane() {
    }

    // GETTER - retorna el número de cola VALIDATION: Integer puede ser null
    public Integer getTailNumber() {
        return tailNumber;
    }

    // SETTER - asigna el número de cola
    public void setTailNumber(Integer tailNumber) {
        this.tailNumber = tailNumber;
    }

    // GETTER - retorna true si tiene piloto automático, false en caso contrario
    public boolean isAutopilot() {
        return autopilot;
    }

    // SETTER - asigna el estado del piloto automático
    public void setAutopilot(boolean autopilot) {
        this.autopilot = autopilot;
    }
}