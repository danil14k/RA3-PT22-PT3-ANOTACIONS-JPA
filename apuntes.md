# Apuntes - Projecte JPA/Hibernate

## Estructura del projecte

El projecte utilitza **Hibernate** com a proveïdor JPA per gestionar la persistència d'objectes Java en una base de dades MySQL.

### Entitats

- **Person** (classe pare): `Person.java` - Representa una persona amb nom, cognom i telèfon.
  - **Student** (subclasse): `Student.java` - Afegeix `studentCode`.
  - **Teacher** (subclasse): `Teacher.java` - Afegeix `teacherCode`.
- **Vehicle** (classe pare): `Vehicle.java` - Representa un vehicle amb marca, any i preu.
  - **Car** (subclasse): `Car.java` - Afegeix `doors` i `seats`.
  - **Motorcycle** (subclasse): `Motorcycle.java` - Afegeix `hasSidecar`.
  - **Plane** (subclasse): `Plane.java` - Afegeix `tailNumber` i `autopilot`.

### Classe principal

`Main.java` (paquet `parte3`) conté un menú interactiu amb 3 fases:
- **Fase 1**: Crear dades de prova (INSERT)
- **Fase 2**: Treure vehicles de persones (UPDATE relació)
- **Fase 3**: Actualitzar un vehicle (UPDATE atributs)

---

## RA3 - PT2: Anotacions JPA

### 1. Com s'indica quin atribut és la clau primària d'una entitat?

S'utilitza l'anotació **`@Id`** sobre l'atribut que serà la clau primària. A més, s'acompanya de **`@GeneratedValue(strategy = GenerationType.IDENTITY)`** perquè la base de dades generi el valor automàticament (autoincrement).

Exemple a `Person.java`, línies 13-15:
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
```

Exemple a `Vehicle.java`, línies 10-12:
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
```

`@Id` marca l'atribut com a clau primària i `@GeneratedValue` amb `IDENTITY` delega la generació del valor a la columna autoincremental de MySQL.

---

### 2. Com has gestionat el vincle entre Persona i Vehicle?

La relació entre `Person` i `Vehicle` és **bidireccional One-to-Many / Many-to-One**:

**Costat Person (One-to-Many)** - `Person.java`, línies 21-22:
```java
@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
private List<Vehicle> vehicles = new ArrayList<>();
```
- `@OneToMany`: Una persona pot tenir molts vehicles.
- `mappedBy = "person"`: Indica que l'atribut `person` de la classe `Vehicle` és el propietari de la relació (gestiona la clau forana).
- `cascade = CascadeType.ALL`: Totes les operacions (persist, merge, remove, etc.) sobre Person es propaguen als seus vehicles.

**Costat Vehicle (Many-to-One)** - `Vehicle.java`, línies 18-20:
```java
@ManyToOne
@JoinColumn(name = "person_id")
private Person person;
```
- `@ManyToOne`: Molts vehicles poden pertànyer a una sola persona.
- `@JoinColumn(name = "person_id")`: Crea la columna `person_id` a la taula `vehicle` com a clau forana que referencia la taula `person`.

**Mètodes auxiliars per mantenir la coherència** - `Person.java`, línies 27-35:
```java
public void addVehicle(Vehicle vehicle) {
    vehicles.add(vehicle);
    vehicle.setPerson(this);
}

public void removeVehicle(Vehicle vehicle) {
    vehicles.remove(vehicle);
    vehicle.setPerson(null);
}
```
Aquests mètodes asseguren que ambdós costats de la relació es mantinguin sincronitzats.

---

### 3. Quina estratègia d'herència hi ha a la classe Person?

La classe `Person` utilitza l'estratègia **SINGLE_TABLE** (Taula Única).

`Person.java`, línies 9-10:
```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
```

**Com es reflecteix a la base de dades:**
- Totes les subclasses (`Student`, `Teacher`) s'emmagatzemen en una **única taula** anomenada `person`.
- La columna **`person_type`** (discriminador) diferencia quin tipus de persona és cada fila.
- `Student.java` línia 6: `@DiscriminatorValue("Student")` → el valor "Student" s'insereix a `person_type`.
- `Teacher.java` línia 6: `@DiscriminatorValue("Teacher")` → el valor "Teacher" s'insereix a `person_type`.
- Les columnes específiques de subclasses (`studentCode`, `teacherCode`) apareixen a la mateixa taula, amb valors `NULL` per les files que no són d'aquell tipus.

**Nota:** La jerarquia `Vehicle` utilitza una estratègia diferent, **JOINED** (`Vehicle.java`, línia 7), on cada subclasse (`Car`, `Motorcycle`, `Plane`) té la seva pròpia taula i es fan JOINs amb la taula pare `vehicle`.

---

## RA3 - PT3: Operacions CRUD amb JPA

### 4. Gestiones la inserció de duplicats?

**No, no es gestiona la inserció de duplicats de forma explícita.** No hi ha cap comprovació prèvia (query) ni restriccions `@Column(unique = true)` que impedeixin duplicats per nom, cognom o altres camps.

**Què passaria si executes dues vegades la fase1 sense sortir del programa?**
- Es crearien **registres duplicats**. Com que els IDs es generen automàticament amb `@GeneratedValue(IDENTITY)`, cada execució assignaria nous IDs. Per tant, tindríem 8 persones i 8 vehicles en comptes de 4 i 4. No hi hauria error, simplement dades duplicades.

**Què passaria si tornes a executar tot el programa des de zero?**
- Gràcies a la propietat `hibernate.hbm2ddl.auto = create-drop` (veure pregunta 5), en tornar a arrencar el programa l'esquema es **recrea des de zero**: primer es fan DROP de totes les taules i després es creen de nou buides. Per tant, **no hi hauria duplicats** ja que la base de dades comença neta cada cop que s'inicia el programa.

---

### 5. Quina funció té la propietat `hibernate.hbm2ddl.auto = create-drop`?

Fitxer `hibernate.cfg.xml`, línia 21:
```xml
<property name="hibernate.hbm2ddl.auto">create-drop</property>
```

Aquesta propietat controla com Hibernate gestiona l'esquema de la base de dades:

- **`create`** (a l'inici): Quan es crea el `SessionFactory`, Hibernate **elimina les taules existents** (DROP) i **crea l'esquema de nou** a partir de les anotacions de les entitats.
- **`drop`** (al final): Quan es tanca el `SessionFactory` (línia 58 de `Main.java`: `HibernateSession.getSessionFactory().close()`), Hibernate **elimina totes les taules**.

**Conseqüències:**
- Cada vegada que s'inicia el programa, la base de dades comença **buida**.
- Totes les dades es **perden** en tancar el programa.
- Només és adequat per a **desenvolupament i proves**, mai per a producció.
- Alternatives: `update` (actualitza l'esquema sense esborrar dades), `validate` (només valida l'esquema), `none` (no fa res).

---

### 6. Explica pas a pas com actualitzes les dades (fase 3). Diferències amb la fase 2.

#### Fase 3: Actualitzar atributs d'un vehicle (`Main.java`, línies 125-152)

**Pas a pas:**
1. **Obrir sessió i transacció** (línies 126-127):
   ```java
   Session session = HibernateSession.getSessionFactory().openSession();
   Transaction transaction = session.beginTransaction();
   ```
2. **Recuperar l'entitat** amb `session.get()` (línia 130):
   ```java
   Vehicle vehicle = session.get(Vehicle.class, 1);
   ```
3. **Comprovar tipus i fer cast** (línia 132-133):
   ```java
   if (vehicle != null && vehicle instanceof Car) {
       Car car = (Car) vehicle;
   ```
4. **Modificar els atributs** del vehicle (línies 134-137):
   ```java
   car.setBrand("Mercedes-Benz");
   car.setModel("C-Class");
   car.setYear(2023);
   car.setDoors(4);
   ```
5. **Actualitzar i fer commit** (línies 139-140):
   ```java
   session.update(car);
   transaction.commit();
   ```
6. **Tancar sessió** al `finally` (línia 150).

#### Fase 2: Desvincular un vehicle del seu propietari (`Main.java`, línies 101-123)

**Pas a pas:**
1. Obrir sessió i transacció (línies 102-103).
2. Recuperar el vehicle amb ID 1 (línia 106):
   ```java
   Vehicle vehicle = session.get(Vehicle.class, 1);
   ```
3. Posar la referència al propietari a `null` (línia 109):
   ```java
   vehicle.setOwner(null);
   ```
4. Actualitzar i fer commit (línies 110-111).
5. Tancar sessió.

#### Diferències clau:

| Aspecte | Fase 2 (Desvincular) | Fase 3 (Actualitzar) |
|---|---|---|
| **Què modifica** | La **relació** (clau forana `person_id`) | Els **atributs propis** del vehicle (brand, year, doors...) |
| **Operació SQL** | `UPDATE vehicle SET person_id = NULL WHERE id = 1` | `UPDATE vehicle SET brand = ..., year = ... WHERE id = 1` + `UPDATE car SET doors = ... WHERE id = 1` |
| **Casting** | No necessita cast, treballa amb `Vehicle` directament | Necessita `instanceof` + cast a `Car` per accedir a atributs específics de la subclasse |
| **Taules afectades** | Només la taula `vehicle` (on està la FK) | Dues taules: `vehicle` (atributs comuns) i `car` (atributs específics), ja que Vehicle usa herència JOINED |
| **Validació** | Només comprova `vehicle != null` | Comprova `vehicle != null` **i** `vehicle instanceof Car` |
