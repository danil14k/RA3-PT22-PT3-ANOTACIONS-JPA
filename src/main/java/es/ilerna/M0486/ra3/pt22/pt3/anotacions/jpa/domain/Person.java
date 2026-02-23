package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// ENTITY - Person - Clase padre para Student y Teacher
// Usa estrategia de herencia SINGLE_TABLE con discriminador person_type
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
public class Person {

    // ATRIBUTO - id - Clave primaria auto-generada KEYWORD: ID, PRIMARY_KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ATRIBUTO - name - Nombre de la persona VALIDATION: puede ser null
    private String name;
    // ATRIBUTO - surname - Apellido de la persona VALIDATION: puede ser null
    private String surname;
    // ATRIBUTO - phoneNumber - Número de teléfono VALIDATION: puede ser null, Integer
    private Integer phoneNumber;

    // RELACIÓN - vehicles - Lista bidireccional con Vehicle (mappedBy person)
    // CASCADE ALL permite eliminar vehículos con la persona
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

    // CONSTRUCTOR por defecto para JPA
    public Person() {
    }

    // MÉTODO - addVehicle - Añade un vehículo a la lista y establece la relación inversa
    // KEYWORD: ADD_VEHICLE, RELACIÓN BIDIRECCIONAL
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        vehicle.setPerson(this);
    }

    // MÉTODO - removeVehicle - Elimina un vehículo de la lista y anula la relación
    // KEYWORD: REMOVE_VEHICLE, RELACIÓN BIDIRECCIONAL
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
        vehicle.setPerson(null);
    }

    // GETTER - retorna el id de la persona
    public Integer getId() {
        return id;
    }

    // SETTER - asigna el id de la persona
    public void setId(Integer id) {
        this.id = id;
    }

    // GETTER - retorna el nombre de la persona
    public String getName() {
        return name;
    }

    // SETTER - asigna el nombre de la persona
    public void setName(String name) {
        this.name = name;
    }

    // GETTER - retorna el apellido de la persona
    public String getSurname() {
        return surname;
    }

    // SETTER - asigna el apellido de la persona
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // GETTER - retorna el número de teléfono
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    // SETTER - asigna el número de teléfono
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // GETTER - retorna la lista de vehículos asociados KEYWORD: VEHICLES
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    // SETTER - asigna la lista de vehículos asociados
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}