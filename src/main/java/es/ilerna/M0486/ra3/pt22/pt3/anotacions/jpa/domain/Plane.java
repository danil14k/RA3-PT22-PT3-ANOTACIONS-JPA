package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;

@Entity
@Table(name = "plane")
@PrimaryKeyJoinColumn(name = "id")
public class Plane extends Vehicle {

    private Integer tailNumber;
    private boolean autopilot;

    public Plane() {
    }

    public Integer getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(Integer tailNumber) {
        this.tailNumber = tailNumber;
    }

    public boolean isAutopilot() {
        return autopilot;
    }

    public void setAutopilot(boolean autopilot) {
        this.autopilot = autopilot;
    }
}