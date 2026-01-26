package es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main;

import org.hibernate.Session;
import org.hibernate.Transaction;
import es.ilerna.M0486.ra3.pt22.pt3.anotacions.jpa.main.*;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateSession.getSessionFactory().openSession();
			tx = session.beginTransaction();

			Student s1 = new Student("Ana", "ana@example.com", 8.7);
			Student s2 = new Student("Luis", "luis@example.com", 7.5);

			Teacher t1 = new Teacher("Marta", "marta@uni.edu", 2500.0);

			Car car = new Car("Toyota", 5);
			Plane plane = new Plane("Boeing", 150);
			Motorcycle moto = new Motorcycle("Yamaha", "Deportiva");

			session.persist(s1);
			session.persist(s2);
			session.persist(t1);
			session.persist(car);
			session.persist(plane);
			session.persist(moto);

			tx.commit();

			List<Student> students = session.createQuery("from Student", Student.class).list();
			System.out.println("Lista de estudiantes:");
			for (Student s : students) {
				System.out.println(" - " + s.getName() + " (nota: " + s.getGrade() + ")");
			}

			List<Vehicle> vehicles = session.createQuery("from Vehicle", Vehicle.class).list();
			System.out.println("Lista de vehicles:");
			for (Vehicle v : vehicles) {
				System.out.println(" - " + v.getBrand() + " -> " + v.getClass().getSimpleName());
			}

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			HibernateSession.getSessionFactory().close();
		}
	}
}