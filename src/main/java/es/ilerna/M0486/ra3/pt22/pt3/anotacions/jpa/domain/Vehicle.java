package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;

// ENTITY - Vehicle - Clase padre para Car, Motorcycle, Plane
// Usa estrategia de herencia JOINED: crea tabla principal vehicle + tablas específicas para subclases
@Entity
@Table(name = "vehicle")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle {

    // ATRIBUTO - id - Clave primaria auto-generada KEYWORD: ID, PRIMARY_KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ATRIBUTO - brand - Marca del vehículo VALIDATION: String puede ser null
    private String brand;
    // ATRIBUTO - year - Año de fabricación del vehículo VALIDATION: Integer puede ser null
    private Integer year;
    // ATRIBUTO - price - Precio del vehículo VALIDATION: Float puede ser null
    private Float price;

    // RELACIÓN - person - Relación ManyToOne con Person (varios vehículos por persona)
    // KEYWORD: FOREIGN_KEY, PERSON_ID, RELACIÓN BIDIRECCIONAL
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    // CONSTRUCTOR por defecto para JPA
    public Vehicle() {
    }

    // GETTER - retorna el id del vehículo
    public Integer getId() {
        return id;
    }

    // SETTER - asigna el id del vehículo
    public void setId(Integer id) {
        this.id = id;
    }

    // GETTER - retorna la marca del vehículo
    public String getBrand() {
        return brand;
    }

    // SETTER - asigna la marca del vehículo
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // GETTER - retorna el año del vehículo
    public Integer getYear() {
        return year;
    }

    // SETTER - asigna el año del vehículo
    public void setYear(Integer year) {
        this.year = year;
    }

    // GETTER - retorna el precio del vehículo
    public Float getPrice() {
        return price;
    }

    // SETTER - asigna el precio del vehículo
    public void setPrice(Float price) {
        this.price = price;
    }

    // GETTER - retorna la persona propietaria del vehículo KEYWORD: OWNER, PERSON
    public Person getPerson() {
        return person;
    }

    // SETTER - asigna la persona propietaria del vehículo
    public void setPerson(Person person) {
        this.person = person;
    }
}