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

public class Main {

	private static List<Person> people = new ArrayList<>();
	private static List<Vehicle> vehicles = new ArrayList<>();
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcio;

		do {
			System.out.println("\n===== MENÚ PRINCIPAL =====");
			System.out.println("1) Fase 1: Crear dades de prova");
			System.out.println("2) Fase 2: Treure vehicles de persones");
			System.out.println("3) Fase 3: Actualitzar un vehicle");
			System.out.println("0) Sortir");
			System.out.print("Escull una opció: ");

			opcio = sc.nextInt();
			sc.nextLine();

			switch (opcio) {
				case 1:
					fase1();
					break;
				case 2:
					fase2();
					break;
				case 3:
					fase3();
					break;
				case 0:
					System.out.println("Fins aviat!");
					break;
				default:
					System.out.println("Opció incorrecta.");
			}

		} while (opcio != 0);

		sc.close();
		HibernateSession.getSessionFactory().close();
	}
	
	private static void fase1() {
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		try {
			Person p1 = new Person("Juan", "González");
			Person p2 = new Person("María", "López");
			Person p3 = new Student("Carlos", "Martínez", "DAM2");
			Person p4 = new Teacher("Ana", "Fernández", "Programación");
			
			session.save(p1);
			session.save(p2);
			session.save(p3);
			session.save(p4);
			
			Car car1 = new Car("Toyota", "Corolla", 2020, 4);
			Car car2 = new Car("BMW", "X5", 2021, 5);
			Motorcycle moto1 = new Motorcycle("Harley-Davidson", "Street 750", 2019, 750);
			Plane plane1 = new Plane("Boeing", "747", 2015, 8);
			
			car1.setOwner(p1);
			car2.setOwner(p2);
			moto1.setOwner(p3);
			plane1.setOwner(p4);
			
			session.save(car1);
			session.save(car2);
			session.save(moto1);
			session.save(plane1);
			
			transaction.commit();
			System.out.println("Fase 1 completada: Datos de prueba creados correctamente.");
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Error en Fase 1: " + e.getMessage());
		} finally {
			session.close();
		}
	}
	
	private static void fase2() {
		
	}

	private static void fase3() {
		}
	}

