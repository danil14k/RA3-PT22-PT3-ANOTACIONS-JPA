package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;

// ENTITY - Car - Vehicle subclass
// Hereda de Vehicle usando estrategia de herencia JOINED
@Entity
@Table(name = "car")
@PrimaryKeyJoinColumn(name = "id")
public class Car extends Vehicle {

    // ATRIBUTO - doors - número de puertas del coche
    private Integer doors;
    // ATRIBUTO - seats - número de asientos del coche
    private Integer seats;

    // CONSTRUCTOR por defecto para JPA
    public Car() {
    }

    // GETTER - retorna el número de puertas VALIDATION: Integer puede ser null
    public Integer getDoors() {
        return doors;
    }

    // SETTER - asigna el número de puertas VALIDATION: requiere Integer válido
    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    // GETTER - retorna el número de asientos VALIDATION: Integer puede ser null
    public Integer getSeats() {
        return seats;
    }

    // SETTER - asigna el número de asientos VALIDATION: requiere Integer válido
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}