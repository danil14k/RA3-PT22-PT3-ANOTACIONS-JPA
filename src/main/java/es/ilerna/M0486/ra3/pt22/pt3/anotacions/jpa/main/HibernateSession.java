package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Car;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Motorcycle;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Person;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Plane;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Student;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Teacher;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.domain.Vehicle;

// CLASE - HibernateSession - Patrón Singleton para gestionar SessionFactory
// KEYWORD: HIBERNATE, SESSION_FACTORY, SINGLETON, CONFIGURATION
public class HibernateSession {

	// ATRIBUTO - sessionFactory - Instancia única del SessionFactory inicializada al cargar la clase
	private static final SessionFactory sessionFactory = buildSessionFactory();

	// MÉTODO - buildSessionFactory - Construye el SessionFactory con todas las entidades anotadas
	// KEYWORD: HIBERNATE_CONFIGURATION, ANNOTATION_MAPPING, SERVICE_REGISTRY, ERROR_HANDLING
	private static SessionFactory buildSessionFactory() {
		try {
			// PASO 1: Crear configuración de Hibernate
			Configuration configuration = new Configuration();

			// PASO 2: Cargar propiedades desde archivo de configuración (hibernate.cfg.xml)
			configuration.configure();

			// PASO 3: Registrar todas las entidades del dominio KEYWORD: ENTITIES, MAPPING
			configuration.addAnnotatedClass(Person.class); // ENTITY: Clase padre
			configuration.addAnnotatedClass(Student.class); // ENTITY: Subclase de Person
			configuration.addAnnotatedClass(Teacher.class); // ENTITY: Subclase de Person
			configuration.addAnnotatedClass(Vehicle.class); // ENTITY: Clase padre
			configuration.addAnnotatedClass(Car.class); // ENTITY: Subclase de Vehicle
			configuration.addAnnotatedClass(Plane.class); // ENTITY: Subclase de Vehicle
			configuration.addAnnotatedClass(Motorcycle.class); // ENTITY: Subclase de Vehicle

			// PASO 4: Construir el ServiceRegistry con las propiedades configuradas
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			// PASO 5: Crear y retornar el SessionFactory KEYWORD: FACTORY_BUILD
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			// MANEJO DE EXCEPCIONES: Error en la construcción del SessionFactory VALIDATION: Critical
			System.err.println("Error creando SessionFactory: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	// MÉTODO - getSessionFactory - Retorna la instancia única del SessionFactory KEYWORD: SINGLETON_ACCESS
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}