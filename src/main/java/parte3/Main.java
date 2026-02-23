package parte3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Car;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Motorcycle;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Person;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Plane;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Student;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Teacher;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Vehicle;

// CLASE - Main - Aplicación con menú interactivo de 3 fases para gestionar Personas y Vehículos
// KEYWORD: MENU, INTERACTIVO, HIBERNATE_OPERATIONS, CRUD
public class Main {

	// ATRIBUTO - people - Lista estática para almacenar personas en memoria
	private static List<Person> people = new ArrayList<>();
	// ATRIBUTO - vehicles - Lista estática para almacenar vehículos en memoria
	private static List<Vehicle> vehicles = new ArrayList<>();

	// MÉTODO - main - Punto de entrada de la aplicación con menú de opciones
	// KEYWORD: MAIN_METHOD, SWITCH_CASE, MENU_LOOP, CONTROL_FLOW
	public static void main(String[] args) {

		// VARIABLE - sc - Scanner para entrada de usuario
		Scanner sc = new Scanner(System.in);
		// VARIABLE - opcio - Opción seleccionada en el menú (0-3)
		int opcio;

		// BUCLE - do-while - Menú que se repite hasta que el usuario elige salir (0)
		do {
			System.out.println("\n===== MENÚ PRINCIPAL =====");
			System.out.println("1) Fase 1: Crear dades de prova");
			System.out.println("2) Fase 2: Treure vehicles de persones");
			System.out.println("3) Fase 3: Actualitzar un vehicle");
			System.out.println("0) Sortir");
			System.out.print("Escull una opció: ");

			// VALIDACIÓN: Capturar entrada numérica KEYWORD: INPUT_VALIDATION
			opcio = sc.nextInt();
			sc.nextLine(); // Consumir el salto de línea del buffer

			// FLUJO: Switch para ejecutar la fase correspondiente
			switch (opcio) {
				case 1:
					// OPCIÓN 1: Fase 1 - Crear datos de prueba KEYWORD: CREATE_TEST_DATA
					fase1();
					break;
				case 2:
					// OPCIÓN 2: Fase 2 - Eliminar asociación persona-vehículo KEYWORD: DISSOCIATION
					fase2();
					break;
				case 3:
					// OPCIÓN 3: Fase 3 - Actualizar vehículo existente KEYWORD: UPDATE_VEHICLE
					fase3();
					break;
				case 0:
					// OPCIÓN 0: Salir de la aplicación
					System.out.println("Fins aviat!");
					break;
				default:
					// VALIDACIÓN: Opción no válida
					System.out.println("Opció incorrecta.");
			}

		} while (opcio != 0); // CONDICIÓN: Salir cuando opcio == 0

		// LIMPIEZA: Cerrar recursos KEYWORD: CLEANUP
		sc.close();
		// SHUTDOWN: Cerrar SessionFactory de Hibernate
		HibernateSession.getSessionFactory().close();
	}

	// ============================================================
	// FASE 1: CREAR DATOS DE PRUEBA
	// KEYWORD: FASE1, CREATE_TEST_DATA, PERSIST_ENTITIES, RELATIONSHIPS
	// ============================================================
	private static void fase1() {
		// PASO 1: Crear sesión de Hibernate KEYWORD: SESSION_CREATION
		Session session = HibernateSession.getSessionFactory().openSession();
		// PASO 2: Iniciar transacción KEYWORD: TRANSACTION_BEGIN
		Transaction transaction = session.beginTransaction();

		try {
			// BLOQUE 1: Crear personas de prueba (Person, Student, Teacher)
			Person p1 = new Person("Juan", "González"); // KEYWORD: PERSON, CREATE
			Person p2 = new Person("María", "López"); // KEYWORD: PERSON, CREATE
			Person p3 = new Student("Carlos", "Martínez", "DAM2"); // KEYWORD: STUDENT, CREATE, CODE
			Person p4 = new Teacher("Ana", "Fernández", "Programación"); // KEYWORD: TEACHER, CREATE, CODE

			// PERSISTENCIA: Guardar personas en base de datos KEYWORD: SAVE, PERSIST
			session.save(p1);
			session.save(p2);
			session.save(p3);
			session.save(p4);

			// BLOQUE 2: Crear vehículos de prueba (Car, Motorcycle, Plane)
			Car car1 = new Car("Toyota", "Corolla", 2020, 4); // KEYWORD: CAR, CREATE, DOORS
			Car car2 = new Car("BMW", "X5", 2021, 5); // KEYWORD: CAR, CREATE, DOORS
			Motorcycle moto1 = new Motorcycle("Harley-Davidson", "Street 750", 2019, 750); // KEYWORD: MOTORCYCLE, CREATE
			Plane plane1 = new Plane("Boeing", "747", 2015, 8); // KEYWORD: PLANE, CREATE, TAIL_NUMBER

			// BLOQUE 3: Establecer relaciones Person-Vehicle (Asociación propietario)
			// KEYWORD: RELATIONSHIP, BIDIRECTIONAL_ASSOCIATION
			car1.setOwner(p1); // Car1 pertenece a p1
			car2.setOwner(p2); // Car2 pertenece a p2
			moto1.setOwner(p3); // Moto1 pertenece a p3 (Student)
			plane1.setOwner(p4); // Plane1 pertenece a p4 (Teacher)

			// PERSISTENCIA: Guardar vehículos con sus asociaciones
			session.save(car1);
			session.save(car2);
			session.save(moto1);
			session.save(plane1);

			// CONFIRMACIÓN: Hacer commit de la transacción KEYWORD: COMMIT, FINALIZE
			transaction.commit();
			System.out.println("Fase 1 completada: Datos de prueba creados correctamente.");
		} catch (Exception e) {
			// MANEJO DE ERRORES: Deshacer cambios si ocurre excepción KEYWORD: ROLLBACK, ERROR_HANDLING
			transaction.rollback();
			System.out.println("Error en Fase 1: " + e.getMessage());
		} finally {
			// LIMPIEZA: Cerrar sesión KEYWORD: CLEANUP, SESSION_CLOSE
			session.close();
		}
	}

	// ============================================================
	// FASE 2: DISOCIAR VEHÍCULO DE PERSONA
	// KEYWORD: FASE2, DISSOCIATION, REMOVE_RELATIONSHIP, UPDATE_VEHICLE
	// ============================================================
	private static void fase2() {
		// PASO 1: Crear sesión de Hibernate
		Session session = HibernateSession.getSessionFactory().openSession();
		// PASO 2: Iniciar transacción
		Transaction transaction = session.beginTransaction();

		try {
			// BÚSQUEDA: Obtener vehículo con ID = 1 KEYWORD: QUERY_BY_ID, GET
			Vehicle vehicle = session.get(Vehicle.class, 1);

			// VALIDACIÓN: Verificar si el vehículo existe
			if (vehicle != null) {
				// OPERACIÓN: Eliminar la relación con la persona KEYWORD: SET_NULL, DISSOCIATE
				vehicle.setOwner(null);
				// ACTUALIZACIÓN: Persistir cambios en la base de datos KEYWORD: UPDATE, PERSIST
				session.update(vehicle);
				// CONFIRMACIÓN: Confirmar cambios
				transaction.commit();
				System.out.println("Fase 2 completada: Asociacion eliminada.");
			} else {
				// VALIDACIÓN: Vehículo no encontrado
				System.out.println("Error: Vehiculo con ID 1 no encontrado.");
				transaction.rollback();
			}
		} catch (Exception e) {
			// MANEJO DE ERRORES: Deshacer cambios
			transaction.rollback();
			System.out.println("Error en Fase 2: " + e.getMessage());
		} finally {
			// LIMPIEZA: Cerrar sesión
			session.close();
		}
	}

	// ============================================================
	// FASE 3: ACTUALIZAR VEHÍCULO
	// KEYWORD: FASE3, UPDATE_VEHICLE, MODIFY_PROPERTIES, INSTANCEOF_VALIDATION
	// ============================================================
	private static void fase3() {
		// PASO 1: Crear sesión de Hibernate
		Session session = HibernateSession.getSessionFactory().openSession();
		// PASO 2: Iniciar transacción
		Transaction transaction = session.beginTransaction();

		try {
			// BÚSQUEDA: Obtener vehículo con ID = 1 KEYWORD: QUERY_BY_ID, GET
			Vehicle vehicle = session.get(Vehicle.class, 1);

			// VALIDACIÓN DOBLE: Verificar que exista y sea un Car KEYWORD: INSTANCEOF, TYPE_CHECK, VALIDATION
			if (vehicle != null && vehicle instanceof Car) {
				// CASTING: Convertir Vehicle a Car para acceder a propiedades específicas
				Car car = (Car) vehicle;
				// ACTUALIZACIÓN: Modificar propiedades del coche KEYWORD: UPDATE_PROPERTIES
				car.setBrand("Mercedes-Benz"); // KEYWORD: BRAND
				car.setModel("C-Class"); // KEYWORD: MODEL
				car.setYear(2023); // KEYWORD: YEAR
				car.setDoors(4); // KEYWORD: DOORS

				// PERSISTENCIA: Guardar cambios en base de datos
				session.update(car);
				// CONFIRMACIÓN: Confirmar transacción
				transaction.commit();
				System.out.println("Fase 3 completada: Vehiculo 1 actualizado correctament.");
			} else {
				// VALIDACIÓN: ID no existe o no es un Car
				System.out.println("Error: Vehiculo con ID 1 no encontrado o no es un coche.");
				transaction.rollback();
			}
		} catch (Exception e) {
			// MANEJO DE ERRORES: Deshacer cambios
			transaction.rollback();
			System.out.println("Error en Fase 3: " + e.getMessage());
		} finally {
			// LIMPIEZA: Cerrar sesión
			session.close();
		}
	}
}

