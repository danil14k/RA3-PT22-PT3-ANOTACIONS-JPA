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

public class HibernateSession {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();

			configuration.configure();

			configuration.addAnnotatedClass(Person.class);
			configuration.addAnnotatedClass(Student.class);
			configuration.addAnnotatedClass(Teacher.class);
			configuration.addAnnotatedClass(Vehicle.class);
			configuration.addAnnotatedClass(Car.class);
			configuration.addAnnotatedClass(Plane.class);
			configuration.addAnnotatedClass(Motorcycle.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Error creando SessionFactory: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}