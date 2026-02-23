# Respuestas - RA3 PT2 y PT3

## RA3 - PT2: Anotaciones JPA

### 1. ¿Cómo se indica qué atributo es la clave primaria de una entidad?

Con `@Id` sobre el atributo. También usamos `@GeneratedValue(strategy = GenerationType.IDENTITY)` para que sea autoincremental. Se puede ver en `Person.java` línea 13 y `Vehicle.java` línea 10.

### 2. ¿Cómo has gestionado el vínculo entre Persona y Vehicle?

Es una relación bidireccional One-to-Many / Many-to-One.
- En `Person.java` línea 21: `@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)` → una persona tiene muchos vehículos.
- En `Vehicle.java` líneas 18-19: `@ManyToOne` con `@JoinColumn(name = "person_id")` → cada vehículo tiene un propietario, y se crea la columna `person_id` como clave foránea.

### 3. ¿Qué estrategia de herencia hay en la clase Person?

SINGLE_TABLE (`Person.java` líneas 9-10). Significa que Person, Student y Teacher se guardan todos en la misma tabla. Para diferenciarlos se usa una columna discriminadora `person_type`, donde cada subclase pone su valor con `@DiscriminatorValue`.

---

## RA3 - PT3: Operaciones CRUD con JPA

### 4. ¿Gestionas la inserción de duplicados?

No, no hay ningún control. Si ejecutas fase1 dos veces sin salir, se crean registros duplicados porque los IDs son autogenerados. Pero si vuelves a ejecutar el programa desde cero, no hay duplicados porque `create-drop` borra y recrea las tablas cada vez que arranca.

### 5. ¿Qué función tiene `hibernate.hbm2ddl.auto = create-drop`?

Cuando arranca el programa crea todas las tablas, y cuando se cierra las elimina. Así cada ejecución empieza con la BBDD vacía. Solo es útil para pruebas, no para producción.

### 6. Explica paso a paso cómo actualizas los datos (fase 3). ¿Diferencias con la fase 2?

Fase 3: se recupera el vehículo con `session.get()`, se comprueba que sea un Car con `instanceof`, se hace cast y se modifican sus atributos (brand, year, doors...), después `session.update()` y `commit`.

Fase 2: se recupera el vehículo, se pone el propietario a null (`setOwner(null)`), `update` y `commit`.

La diferencia es que fase 2 modifica la relación (la clave foránea person_id), mientras que fase 3 modifica los atributos propios del vehículo. Además, fase 3 necesita hacer cast a Car para acceder a los campos específicos de la subclase.
