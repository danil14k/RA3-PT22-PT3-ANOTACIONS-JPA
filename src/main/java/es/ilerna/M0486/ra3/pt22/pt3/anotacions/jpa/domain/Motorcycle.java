package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;

@Entity
@Table(name = "motorcycle")
@PrimaryKeyJoinColumn(name = "id")
public class Motorcycle extends Vehicle {

    private boolean hasSidecar;

    public Motorcycle() {
    }

    public boolean isHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }
} 